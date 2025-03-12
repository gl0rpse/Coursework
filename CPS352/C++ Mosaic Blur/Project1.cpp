
//name: Adriel Colon

//goal: user can blur selected regions of an image of their choice
//      with different levels of blur. the esc key exits the program,
//      I to increase blur and D to decrease, R to reset image, and 
//      S to save the image.

//imports/includes
#include <stdio.h>
#include <iostream>
#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

using namespace cv;
using namespace std;

//variables for use
Mat image;         //matrix variable for image
Mat image_orig;    //original copy of image
Point pt = Point(-1, -1); //point for mouse interaction
bool drawing = false; //boolean to see if user is drawing a selection
int blur_degree = 5;  //size of the blur blocks
unsigned char** arr2D; //create a 2D array to store pixel data

//blurBlock() method
//  uses a 2D array to manipulate pixels and blur a selected region
void blurBlock(Point p1, Point p2) {
    
    //variables
    int sum_r;
    int sum_g;
    int sum_b;
    int count;

    //make sure points are at corners of screen
    int x1 = min(p1.x, p2.x);
    int y1 = min(p1.y, p2.y);
    int x2 = max(p1.x, p2.x);
    int y2 = max(p1.y, p2.y);

    //make sure points stay in image
    x1 = max(0, min(x1, image.cols - 1));
    y1 = max(0, min(y1, image.rows - 1));
    x2 = max(0, min(x2, image.cols - 1));
    y2 = max(0, min(y2, image.rows - 1));

    //go over the selected region and blur
    for (int y = y1; y < y2; y += blur_degree) {
        for (int x = x1; x < x2; x += blur_degree) {
            sum_r = sum_g = sum_b = count = 0;

            //nested for loops to compute average color of blocks
            for (int j = 0; j < blur_degree && (y + j) < image.rows; j++) {
                for (int i = 0; i < blur_degree && (x + i) < image.cols; i++) {
                    int index = (x + i) * 3;  //rgb channels
                    sum_b += arr2D[y + j][index + 0];
                    sum_g += arr2D[y + j][index + 1];
                    sum_r += arr2D[y + j][index + 2];
                    count++;    //increment count
                }
            }

            //calculate average color values
            int avg_b = sum_b / count;
            int avg_g = sum_g / count;
            int avg_r = sum_r / count;

            //apply the average color to the block
            for (int j = 0; j < blur_degree && (y + j) < image.rows; j++) {
                for (int i = 0; i < blur_degree && (x + i) < image.cols; i++) {
                    int index = (x + i) * 3;
                    arr2D[y + j][index + 0] = avg_b;
                    arr2D[y + j][index + 1] = avg_g;
                    arr2D[y + j][index + 2] = avg_r;
                }
            }
        }
    }

    //copy the 2D array back to image variable
    for (int y = 0; y < image.rows; y++) {
        memcpy(image.ptr(y), arr2D[y], image.cols * 3);
    }
}

//onMyMouse() method
//  lets user select rectangular area with mouse
void onMyMouse(int event, int x, int y, int flag, void* data) {
    if (event == EVENT_LBUTTONDOWN) { //begin selection because mouse is clicked
        pt = Point(x, y);
        drawing = true;
    }
    else if (event == EVENT_LBUTTONUP) { //mouse is unclicked
        drawing = false;
        blurBlock(pt, Point(x, y)); //apply blur to area
    }
    else if (drawing) { //while dragging display a rectangle to signify area
        image_orig.copyTo(image); 
        rectangle(image, pt, Point(x, y), Scalar(0, 255, 255), 3);
    }
}

//main method
int main(int argc, char** argv) {
    //load input image by file name
    image = imread("insertIMG.jpg"); 

    //base case to see if image is not properly being read
    if (image.empty()) {
        cout << "Error loading image!" << endl; //display error message
        return -1;
    }

    image.copyTo(image_orig); //keep a backup of the original image to variable image_orig

    //allocate memory for 2D array
    arr2D = new unsigned char* [image.rows];
    for (int y = 0; y < image.rows; y++) {
        arr2D[y] = new unsigned char[image.cols * 3];
        memcpy(arr2D[y], image.data + y * image.cols * 3, image.cols * 3);
    }

    //create a window
    namedWindow("My Window");

    //set mouse callback using window and onMyMouse()
    setMouseCallback("My Window", onMyMouse);

    //while loop to keep window displaying image open
    while (1) {
        imshow("My Window", image);

        char c = waitKey(100);

        //ASCII for ESC key
        if (c == 27) {
            break;  //exit
        } 

        //if i key is hit, increase degree of blurring
        else if (c == 'i' || c == 'I') { 
            blur_degree = min(blur_degree + 5, 50); 
        } 

        //if d key is hit, decrease level of blurring
        else if (c == 'd' || c == 'D') { 
            blur_degree = max(blur_degree - 5, 1); 
        }

        //if r key is hit, reset the image without blur
        else if (c == 'r' || c == 'R') {
            image_orig.copyTo(image);
            for (int y = 0; y < image.rows; y++)
                memcpy(arr2D[y], image_orig.ptr(y), image.cols * 3);
        }
        
        //if s key is hit, save the image
        else if (c == 's' || c == 'S') { 
            imwrite("blurryIMG.jpg", image);
        }
    }
    
}
