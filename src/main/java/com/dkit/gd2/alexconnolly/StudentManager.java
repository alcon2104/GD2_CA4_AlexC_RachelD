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

    public void getStudent()
    {
        if(this.students!= null)
        {
            int idToFind = Integer.parseInt(enterField("student CAO number to find"));
            Student studentToFind = findStudent(idToFind);

            if(studentToFind != null)
            {
                System.out.println(studentToFind);
            }
            else
            {
                System.out.println(Colours.RED + "That student does not exist" + Colours.RESET);
            }
        }
    }
//
    public void addStudent()
    {
        int caoNumber = Integer.parseInt(loopUntilValidEntry("caoNumber"));
        String dateOfBirth = loopUntilValidEntry("dateOfBirth");
        String password = loopUntilValidEntry("password");
        String email = loopUntilValidEntry("email");
        boolean alreadyExists = false;

        for(Student student: students)
        {
            if(student.getCaoNumber()==(caoNumber)){
                System.out.println(Colours.RED+"This student already exists, student will not be added"+Colours.RESET);
                alreadyExists=true;
            }
        }

        if(alreadyExists==false)
        {
            Student addedStudent = new Student(caoNumber, dateOfBirth, password, email);
            Student clonedStudent = new Student(addedStudent);

            this.students.add(addedStudent);
            System.out.println(Colours.GREEN+"Student has been successfully added"+Colours.RESET);
        }
    }

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

    private String loopUntilValidEntry(String entryName)
    {
        boolean loop = true;
        while(loop)
        {
            if(entryName.equals("caoNumber"))
            {
                String caoNum = enterField("caoNumber");

                if (caoNum.matches("[0-9]{8}"))
                {
                    return caoNum;
                }
                else
                {
                    System.out.println("CAO format invalid!");

                }
            }

            else if(entryName.equals("dateOfBirth"))
            {
                String dob = enterField("dateOfBirth");

                if (dob.matches("^[0-3]{1}\\d{1}/[0-1]{1}\\d{1}/20{1}\\d{2}"))
                {
                    return dob;
                }
                else
                {
                    System.out.println("Date of Birth format invalid!");

                }
            }

            else if(entryName.equals("password"))
            {
                String password = enterField("password");
                if(password.length() >= 8 && password.length() <= 16)
                {
                    return password;
                }

                else
                {
                    System.out.println("Password format invalid");
                }
            }

            else if(entryName.equals("email"))
            {
                String email = enterField("email");

                // regex found @ https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
                if (email.matches("^(.+)@(.+)\\.com$"))
                {
                    return email;
                }
                else
                {
                    System.out.println("Email format invalid!");

                }
            }
        }
        return null;
    }

    private String enterField(String field)
    {
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
