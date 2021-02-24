package com.dkit.gd2.alexconnolly;

// Stores all student CAO choices.
// Allows student to make course choices, save them and update them later.
//
// emphasis on speed of access when multiple users are accessing this at same time
//
// This component would interact with a Network component that would, in turn, send
// data over the internet to a web client.
//
// Clone all received and returned objects - encapsulation

import java.util.*;

public class CourseChoicesManager {

    // reference to constructor injected studentManager
    private StudentManager studentManager;

    // reference to constructor injected courseManager
    private CourseManager courseManager;

    // Store all the Course details -  fast access

    // caoNumber, course selection list - for fast access


    // CourseChoicesManager DEPENDS on both the StudentManager and CourseManager to access
    // student details and course details.  So, we receive a reference to each via
    // the constructor.
    // This is called "Dependency Injection", meaning that we
    // inject (or pass in) objects that this class requires to do its job.
    //
    CourseChoicesManager(StudentManager studentManager, CourseManager courseManager) {
        this.studentManager = studentManager;
        this.courseManager = courseManager;
    }

//    public Student getStudentDetails() {
//    }
//
//    public getCourseDetails() {
//    }
//
//    public  getStudentChoices() {
//    }
//
//    void updateChoices() {
//    }
//
//    public  getAllCourses() {
//    }
//
//
    public boolean login()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter your CAO Number:");
        String studentCaoNum = keyboard.nextLine();
        System.out.println("Please enter your date of birth:");
        String studentDob = keyboard.nextLine();
        System.out.println("Please enter your password");
        String studentPassword = keyboard.nextLine();

        if(verifyDateOfBirth(studentDob) && verifyPassword(studentPassword) && verifyCaoNum(studentCaoNum))
        {
            System.out.println("You have logged in successfully");
            return true;
        }
        else
        {
            System.out.println("One or more of your details were incorrect, please try logging in again");
            return false;
        }
    }

    public boolean verifyDateOfBirth(String dateOfBirth)
    {
        /* year month date
        String dobRegex ="^20{1}\\d{2}/[0-1]{1}\\d{1}/[0-3]{1}\\d{1}";
        */
        String dobRegex ="^[0-3]{1}\\d{1}/[0-1]{1}\\d{1}/20{1}\\d{2}";

        if(dateOfBirth != null)
        {
            //4 digits for year, starting with 20, 2 digits for month, firs one can't go over 1 (12 months), 2 digits for days, up to 31
            if(!dateOfBirth.matches(dobRegex))
            {
                System.out.println("Invalid birth date format");
                return false;
            }
            else
            {
                return true;
            }
        }
        else if(dateOfBirth == null)
        {
            return false;
        }
        return false;
    }

    public boolean verifyPassword(String password)
    {
        if(password != null)
        {
            if(password.length() >= 8)
            {
                return true;
            }

            else if(password.length() < 0 || password.length() > 16)
            {
                System.out.println("Password format invalid");
                return false;
            }
        }
        else if(password == null)
        {
            return false;
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
                System.out.println("CAHOHO NO!");
                return false;
            }
        }
        return false;
    }


}
