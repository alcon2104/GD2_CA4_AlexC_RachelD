package com.dkit.gd2.alexconnolly;

// StudentManager encapsulates the storage and ability
// to manipulate student objects



import java.io.*;
import java.util.*;

public class StudentManager {

    // Store all students in data structure
    private Map<Integer, Student> students;
    private static Scanner keyboard = new Scanner(System.in);


    public StudentManager() {
        this.students = new HashMap<>();
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
                this.students.put(caoNum, readInStudent);
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
            for(Map.Entry<Integer, Student> student : students.entrySet())
            {
                studentsFile.write(student.getValue().getCaoNumber()+","+ student.getValue().getDateOfBirth()
                                    + "," +student.getValue().getPassword() + "," +student.getValue().getEmail());
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

    public void addStudent()
    {
        int caoNumber = Integer.parseInt(loopUntilValidEntry("caoNumber"));
        String dateOfBirth = loopUntilValidEntry("dateOfBirth");
        String password = loopUntilValidEntry("password");
        String email = loopUntilValidEntry("email");

        if(students.keySet().contains(caoNumber))
        {
            System.out.println(Colours.RED+"This student already exists, student will not be added"+Colours.RESET);
        }


        else
        {
            Student addedStudent = new Student(caoNumber, dateOfBirth, password, email);
            Student clonedStudent = new Student(addedStudent);

            this.students.put(caoNumber,addedStudent);
            System.out.println(Colours.GREEN+"Student has been successfully added"+Colours.RESET);
        }
    }

    public void removeStudent()
    {
        if(this.students != null)
        {
            int caoToRemove = Integer.parseInt(enterField("CAO number to remove"));
            Student studentToRemove = findStudent(caoToRemove);

            if(studentToRemove != null)
            {
                students.remove(studentToRemove.getCaoNumber());
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

                // base regex found @ https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
                //changed to include .com
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
        if(students.keySet().contains(caoToFind))
        {
            return students.get(caoToFind);
        }

        return null;
    }

    public boolean login()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(Colours.PURPLE+"Please enter your CAO Number:");
        String studentCaoNum = keyboard.nextLine();

        if(verifyCaoNum(studentCaoNum))
        {
            int caoToFind = Integer.parseInt(studentCaoNum);
            if(this.students.keySet().contains(caoToFind))
            {
                Student enteredStudent = students.get(caoToFind);
                System.out.println("Please enter your date of birth:");
                String studentDob = keyboard.nextLine();
                System.out.println("Please enter your password:"+Colours.RESET);
                String studentPassword = keyboard.nextLine();
                if(enteredStudent.getDateOfBirth().equals(studentDob) && enteredStudent.getPassword().equals(studentPassword))
                {
                    System.out.println(Colours.GREEN + "You have logged in successfully"+Colours.RESET);
                    return true;
                }
                else
                {
                    System.out.println(Colours.RED+"Your password or date of birth don't match, " +
                            "please try logging in again"+Colours.RESET);
                    return false;
                }
            }
            else
            {
                System.out.println("This student does not exist.");
            }
        }
        return false;
    }

    public boolean verifyCaoNum(String caoNum)
    {
        if(caoNum != null)
        {
            if (caoNum.matches("[0-9]{8}"))
            {
                return true;
            }
            else
            {
                System.out.println(Colours.RED+"CAO format invalid!"+Colours.RESET);
                return false;
            }
        }
        return false;
    }
//    public void isRegistered(int caoNum)
//    {
//        students.isValid();
//    }
}
