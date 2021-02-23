package com.dkit.gd2.alexconnolly;

// StudentManager encapsulates the storage and ability
// to manipulate student objects



import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {

    // Store all students in data structure
    private List<Student> students;
    private static Scanner keyboard = new Scanner(System.in);


    public StudentManager() {
        this.students = new LinkedList<>();
        // Hardcode some values to get started

        // later, load from text file "students.dat" and populate studentsMap
    }

    public void loadStudentsFromFile() {
        try(Scanner studentsFile = new Scanner(new BufferedReader(new FileReader("students.txt"))))
        {
            String input;
            while(studentsFile.hasNextLine())
            {
                input = studentsFile.nextLine();
                String[] data = input.split(",");
                int caoNum = Integer.parseInt(data[0]);
                String dateOfBirth = data[1];
                String password = data[2];
                String email = data[3];

                Student readInStudent = new Student(caoNum,dateOfBirth,password,email);
                this.students.add(readInStudent);
            }
        }

        catch(FileNotFoundException fne)
        {
            System.out.println(Colours.PURPLE+"Could not load students. If this is the first time running the program" +
                    " this could be fine." + Colours.RESET);
        }
    }

    public void saveStudentsToFile() {
        try(BufferedWriter studentsFile = new BufferedWriter(new FileWriter("students.txt")))
        {
            for(Student student: students)
            {
                studentsFile.write(student.getCaoNumber()+","+ student.getDateOfBirth()+ "," +student.getPassword()
                        + "," +student.getEmail());
                studentsFile.write("\n");
            }
        }
        catch(IOException ioe)
        {
            System.out.println(Colours.RED+"Could not save the students"+Colours.RESET);
        }
    }

    public Student getStudent(int caoNum)
    {
        boolean studentExists = false;

        if(students!=null)
        {
            for (Student student : students) {
                if (student.getCaoNumber() == caoNum)
                {
                    Student clonedStudent = new Student(student);
                    studentExists=true;
                    return clonedStudent;
                }
            }

            if (!studentExists)
            {
                System.out.println(Colours.RED + "Sorry, a student with this CAO number does not exist." + Colours.RESET);
            }
        }
        return null;
    }
//
//    public Student addStudent(int caoNum)
//    {
        //take in student details from keyboard
        //return a copy of the student to the map
//    }
//
    public void removeStudent(/*int caoNum*/)
    {
        if(this.students != null)
        {
            int caoToRemove = Integer.parseInt(enterField("CAO number to remove"));
            Student studentToRemove = findStudent(caoToRemove);

            if(studentToRemove != null){
                students.remove(studentToRemove);
            }
            else
            {
                System.out.println(Colours.RED + "That student does not exist" + Colours.RESET);
            }
        }
    }


    private String enterField(String field) {
        String input;
        System.out.print("Please enter student's " + field + ":>" );

        input = keyboard.nextLine();
        return input;
    }

    private Student findStudent(int caoToFind)
    {
        for(Student student: students)
        {
            if(student.getCaoNumber() == caoToFind)
            {
                return student;
            }
        }
        return null;
    }

//    public void isRegistered(int caoNum)
//    {
//        students.isValid();
//    }
}
