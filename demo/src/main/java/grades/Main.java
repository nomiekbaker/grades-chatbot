package grades;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {		
		Class chosenClass = new Class();
		Student chosenStudent = new Student();
		
		Class science = new Class();
		science.id = 999;
		science.name = "Science";
		
		Class[] classes = {science};
		
		StudentClassGrades sampleScience = new StudentClassGrades();
		sampleScience.course = science;
		
		Student sample = new Student();
		sample.id = 123;
		sample.name = "Sam";
		sample.grades.add(sampleScience);
		sample.grades.get(0).updateGrade("quiz 1", 92.50);
		sample.grades.get(0).updateGrade("quiz 2", 94.50);
		
		Student[] students = {sample};
		
		Teacher teacher1 = new Teacher();
		teacher1.name = "Roy";
		teacher1.id = 456;
		
		List<Teacher> users = new ArrayList<>();
		users.add(teacher1);
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println(
			"Welcome to NeuKaB, a chatbot to assist teachers with managing grades."
			+ " For NeuKaB to help you, please enter your id.");
			
			boolean access = false;
			while(!access) {
				String response = scanner.nextLine();
				try{
					Integer.parseInt(response);
					for(Teacher t : users) {
						if(Integer.toString(t.id).equals(response)) {
							System.out.println("Hi " + t.name);
							System.out.println("To view or modify a student's grades, please provide a student ID.");
							access = true;
							break;
						}
					}
					if(!access) {
						System.out.println("That teacher ID is not in our system, please try again");
					}
				
				}
				catch(NumberFormatException e){
					System.out.println("Not a valid number, please try again");
				}
			}
			
			access = false;
			while(!access) {
				String response = scanner.nextLine();
				try{
					Integer.parseInt(response);
					for(Student s : students) {
						if(Integer.toString(s.id).equals(response)) {
							System.out.println("You selected " + s.name);
							chosenStudent = s;
							access = true;
							break;
						}
					}
					if(!access) {
						System.out.println("That student ID is not in our system, please try again");
					}
				
				}
				catch(NumberFormatException e){
					System.out.println("Not a valid number, please try again");
				}
			}
			
			boolean repeat = true;
			String option = "";
			while(repeat) {
				System.out.println("What would you like to do? create/update/delete a grade or view all grades");
				String response = scanner.nextLine();
				if(response.contains("create")) {
					System.out.println("You want to create a new grade. Please tell me the id of the class the grade is for.");
					option = "create";
					repeat = false;
				}
				else if (response.contains("update")) {
					System.out.println("You want to update an existing grade. Please tell me the id of the class the grade is for.");
					option = "update";
					repeat = false;
				}
				else if (response.contains("delete")) {
					System.out.println("You want to delete a grade. Please tell me the id of the class the grade is for.");
					option = "delete";
					repeat = false;
				}
				else if (response.contains("view") || response.contains("all")) {
					chosenStudent.viewGrades();			
				}
				else {
					System.out.println("Sorry, I'm not sure what you're looking for.");
				}
			}
			repeat = true;
			while(repeat) {
				String response = scanner.nextLine();
				try{
					Integer.parseInt(response);
					for(Class c : classes) {
						if(Integer.toString(c.id).equals(response)) {
							System.out.println("You selected " + c.name);
							chosenClass = c;
							repeat = false;
							break;
						}
					}
					if(repeat) {
						System.out.println("That class ID is not in our system, please try again");
					}
				}
				catch(NumberFormatException e){
					System.out.println("Not a valid number, please try again");
				}
			}

			System.out.println("To " + option + " a grade for " + chosenClass.name + ", please tell me the assignment name.");
			String assignmentName = scanner.nextLine();
			if(option == "create") {
				System.out.println("You want to create a new " + chosenClass.name + " assignment called " + assignmentName + ". What numeric grade (out of 100)?");
				String newGrade = scanner.nextLine();
				
				for(StudentClassGrades scg : chosenStudent.grades) {
					if(scg.course.id == chosenClass.id) {
						scg.updateGrade(assignmentName, Double.parseDouble(newGrade));
						break;
					}
				}
				System.out.println("The assignment " + assignmentName + " has an updated grade of " + newGrade);
				chosenStudent.viewGrades();
			}
			else if (option == "update") {
				System.out.println("You want to update the " + chosenClass.name + " assignment called " + assignmentName);
				boolean assignmentExists = false;
				for(StudentClassGrades scg : chosenStudent.grades) {
					if(scg.course.id == chosenClass.id) {
						if(scg.assignmentGrades.containsKey(assignmentName)) {
							assignmentExists = true;
							System.out.println("The current grade for " + assignmentName + " is " + scg.assignmentGrades.get(assignmentName));
							System.out.println("What new grade should " + assignmentName + " be updated to?");
							String newGrade = scanner.nextLine();
							scg.updateGrade(assignmentName, Double.parseDouble(newGrade));
							System.out.println("The assignment " + assignmentName + " has an updated grade of " + newGrade);
							chosenStudent.viewGrades();
						}
					}
				}
				if(!assignmentExists) {
					System.out.println("There is no assignment named " + assignmentName);
				}
			}
			else if (option == "delete") {
				boolean assignmentExists = false;
				for(StudentClassGrades scg : chosenStudent.grades) {
					if(scg.course.id == chosenClass.id) {
						if(scg.assignmentGrades.containsKey(assignmentName)) {
							assignmentExists = true;
							scg.removeGrade(assignmentName);
							System.out.println("The " + chosenClass.name + " assignment called " + assignmentName + " has been deleted.");
							chosenStudent.viewGrades();
						}
					}
				}
				if(!assignmentExists) {
					System.out.println("There is no assignment named " + assignmentName);
				}
			}
		}
	}
}

class Teacher {
	int id;
	String name;
	Class[] classes;
}

class Class {
	int id;
	String name;
	Student[] students;
	String[] assignments;
}

class StudentClassGrades {
	Class course;
	Student student;
	HashMap<String, Double> assignmentGrades = new HashMap<String, Double>();
	
	void updateGrade(String assignmentName, Double grade){
		if(assignmentGrades.get(assignmentName) != null) {
			removeGrade(assignmentName);
		}
		assignmentGrades.put(assignmentName, grade);
	}
	
	void removeGrade (String assignmentName) {
		assignmentGrades.remove(assignmentName);
	}

	double viewGrade (String assignmentName) {
		return assignmentGrades.get(assignmentName);
	}
}

class Student {
	int id;
	String name;
	List<StudentClassGrades> grades = new ArrayList<>();;
	
	void viewGrades () {
		System.out.println("Current grades for " + name);
		for(StudentClassGrades oneclass : grades) {
			System.out.println("Class: " + oneclass.course.name);
			for(String assignment : oneclass.assignmentGrades.keySet()) {
				System.out.println("Assignment: " + assignment + " Grade: " + oneclass.viewGrade(assignment));
			}
		}	
	}
}
