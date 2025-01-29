
//name: Adriel Colon

//goals: linux program that uses open() read() write() and close() to cound U's and D's 
// in files


//imports
#include <fcntl.h>
#include<stdio.h>
#include <unistd.h> 
#include <stdlib.h>

//main method
int main(int argc, char *argv[]) {
	
	//name of executable
	argv[0] = "./countUD";
	
	//variables for use
	int totalUDs = 0;
	int nonUDs = 0;
	int fd;		//file descriptor
	int sz; 	//data size
	char* buffer = (char*)calloc(100, sizeof(char));    //buffer for file data
	
	//for loop to iterate through file arguments
	for (int i = 1; i < argc; i++){
	
		fd = open(argv[i], O_RDONLY); //opens the file in "read only" mode
		
		//checks if file fails to open
		if(fd == -1){
			perror("ERROR");
			continue;	//skips to the next file
		}
		
		//while loop to read current file
		while ((sz = read(fd, buffer, 100)) > 0){
		
			for (int x = 0; x < sz; x++){
				//if character found is a U or D, increment totalUDs
				if (buffer[x] == 'U' || buffer[x] == 'D'){
					totalUDs = totalUDs + 1;
				}
				//if not, increment nonUDs
				else if (buffer[x] != '\n' && buffer[x] != '\r' && buffer[x] != '\0') {
					nonUDs = nonUDs + 1;
				}
			}//end for loop
		}//end while loop
		
		//checks if there is a reading error
		if (sz == -1){
			perror("ERROR");
		}
		
		//close file
		close(fd);	
	
	}//end main for loop
	
	//output results found
	char output[64];
	int len = snprintf(output, sizeof(output), "Non UD Characters: %d\n", nonUDs);
	write(2, output, len); //print out to stderr
	
	//if any UD's are found...
	if (totalUDs > 0){
		len = snprintf(output, sizeof(output), "Total U's and D's Found: %d\n", totalUDs);
		write(1, output, len); //print to stdout
	}
	
	//returns based on U's and D's found
	if (totalUDs > 0){
		return 0;
	}
	
	else{
		return 1;
	}
	
} //end main
