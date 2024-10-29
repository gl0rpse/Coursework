// StudentUser.java
import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;


public class StudentUser {
	/*
	 *
	 * Assignment: 6
	 *
	 * Description: This program serves as a user for the Student.java class,
	 * 				taking a list of data and returning it in a sorted manner by 
	 * 				student ID.
	 *    
	 *
	 * Bug Report: No bugs
	 * 
	 * 
	 */
    
    public static void main(String[] args) throws FileNotFoundException {
    	
        //create an arraylist full of student objects
        ArrayList<Student> students = new ArrayList<>();

        //get an input file
        File courses = new File("courses.txt");
        Scanner in = new Scanner(courses);

        //parse data from txt file and create student objects using data
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] parts = line.split("\t");
            int studentID = Integer.parseInt(parts[0]);
            String courseNumber = parts[1];
            int sectionNumber = Integer.parseInt(parts[2]);
            String semester = parts[3];
            int year = Integer.parseInt(parts[4]);
            students.add(new Student(studentID, courseNumber, sectionNumber, semester, year));
        }

        //sort students by data
        Collections.sort(students);

        //user can select a place to save the file
        JFileChooser chooser = new JFileChooser();
        int userSelection = chooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File output = chooser.getSelectedFile();

            //create printwriter
            PrintWriter out = new PrintWriter(output + ".txt");

            //return sorted student data to new file
            for (Student x : students) {
                out.println(x.toString());
            }

            out.close(); //close printwriter
        }

        //system message to signify file has been saved
        System.out.println("Success! The file has been saved to a location of your choice.");
    }
}
