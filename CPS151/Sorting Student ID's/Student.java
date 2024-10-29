// Student.java

import java.util.Scanner;

public class Student implements Comparable<Student> {
	/*
	 * Assignment: 6
	 *
	 * Description: This program creates a Student object and also utilizes the
	 * Comparable method to use Collections.sort() in the StudentUser.java class.
	 * 
	 *
	 * Bug Report: No bugs
	 * 
	 * 
	 */

	//instance variables
	private int studentID;
	private String courseNumber;
	private int sectionNumber;
	private String semester;
	private int year;

	//constructor
	public Student(int id, String cN, int sN, String Semester, int Year) {
		studentID = id;
		courseNumber = cN;
		sectionNumber = sN;
		semester = Semester;
		year = Year;
	}

	//method that prints all the student data separated by tabs as a string value
	public String toString() {
		return studentID + "\t" + courseNumber + "\t" + sectionNumber + "\t" + semester + "\t" + year;
	}

	//compareTo method utilizing the Comparable interface
	public int compareTo(Student otherStudent) {
		return Integer.compare(this.studentID, otherStudent.studentID);
	}

}
