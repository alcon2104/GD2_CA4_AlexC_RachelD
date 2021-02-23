package com.dkit.gd2.alexconnolly;

import java.util.List;
import java.util.Scanner;

// Test to see if repo works

/**
 *
 * Notes:
 *  Synchronisation of multiple reads and writes to file is not considered here.
 *
 */
public class App
{
    private static Scanner keyboard = new Scanner(System.in);
    public static void main( String[] args )
    {
        System.out.println( "CAO Online - CA4" );
        new App() .start();
    }

    private void start() {


        StudentManager studentManager = new StudentManager();
        studentManager.loadStudentsFromFile();

        CourseManager courseManager= new CourseManager();
        //coursesManager.loadCoursesFromFile();


        // load manager to provide functionality to allow a student
        // to login and add/update their course selections
        // This CourseChoicesManager component depends on the
        // StudentManager and the CourseManager,
        // so we 'inject' or pass-in these objects.
        //
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);

        //test runs for methods
        //StudentManager
//        System.out.println(studentManager.getStudent(78231230));
//        studentManager.removeStudent();


        // display a menu to do things
        doMainMenuLoop(studentManager, courseManager);

        // manual testing of mgr public interface

//        if ( mgr.login(22224444, "xxxx","bbbb") )
//        {
//            Student student = mgr.getStudentDetails(22224444);
//
//            System.out.println("Student: " + student);
//        }
//        else
//            System.out.println("Not logged in - try again");

        //mgr.saveToFile();
        studentManager.saveStudentsToFile();
    }

    private void doMainMenuLoop(StudentManager studentManager, CourseManager courseManager)
    {
        boolean loop = true;
        MainMenu menuOption;
        int option;
        while(loop)
        {
            printMainMenu();
            try
            {
                String input = keyboard.nextLine();
                if(input.isEmpty() || input.length()>1) {
                    throw new IllegalArgumentException();
                }
                else
                {
                    option = Integer.parseInt(input);
                }

                if(option <0 || option >=MainMenu.values().length)
                {
                    throw new IllegalArgumentException();
                }

                menuOption = MainMenu.values()[option];
                switch(menuOption)
                {
                    case  QUIT_APPLICATION:
                        loop = false;
                        break;
                    case SIGN_IN:
                        //signInVerification();
                        break;
                    case DISPLAY_STUDENT_MENU:
                        doStudentMenuLoop(studentManager);
                        break;
                    case DISPLAY_COURSES_MENU:
                        doCoursesMenuLoop(courseManager);
                        break;
                }
            }
            catch(IllegalArgumentException iae)
            {
                System.out.println(Colours.RED+"Please enter a single int"+Colours.RESET);
            }
        }
        System.out.println(Colours.GREEN+"Thank you for using the CAO app"+ Colours.RESET);
    }

    private void printMainMenu()
    {
        System.out.println("\n Options to select:");
        for(int i = 0; i<MainMenu.values().length; i++){
            System.out.println("\t"+ Colours.BLUE + i + ". " + MainMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }

    private void doStudentMenuLoop(StudentManager studentManager) {
        boolean loop = true;
        StudentMenu menuOption;
        int option;
        while(loop)
        {
            printStudentMainMenu();
            try
            {
                String input = keyboard.nextLine();
                if(input.isEmpty() || input.length()>1) {
                    throw new IllegalArgumentException();
                }
                else
                {
                    option = Integer.parseInt(input);
                }

                if(option <0 || option >=StudentMenu.values().length)
                {
                    throw new IllegalArgumentException();
                }
                menuOption = StudentMenu.values()[option];
                switch(menuOption)
                {
                    case  QUIT_STUDENT_MENU:
                        loop = false;
                        break;
                    case GET_STUDENT_DETAILS:
                       // studentManager.printStudent();
                        break;
                    case GET_STUDENT_CHOICES:
                        // studentManager.getStudentChoices();
                        break;
                    case UPDATE_CHOICE:
                       //studentManager.updateChoice();
                        break;

                }
            }
            catch(IllegalArgumentException iae)
            {
                System.out.println(Colours.RED+"Please enter a single int"+Colours.RESET);
            }
        }
    }

    private void printStudentMainMenu() {
        System.out.println("\n Options to select:");
        for(int i = 0; i<StudentMenu.values().length; i++){
            System.out.println("\t"+ Colours.BLUE + i + ". " + StudentMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }


    private void doCoursesMenuLoop(CourseManager courseManager)
    {
        boolean loop = true;
        CoursesMenu menuOption;
        int option;
        while(loop)
        {
            printCoursesMenu();
            try
            {
                String input = keyboard.nextLine();
                if(input.isEmpty() || input.length()>1) {
                    throw new IllegalArgumentException();
                }
                else
                {
                    option = Integer.parseInt(input);
                }

                if(option <0 || option >=CoursesMenu.values().length)
                {
                    throw new IllegalArgumentException();
                }
                menuOption =CoursesMenu.values()[option];
                switch(menuOption)
                {
                    case  QUIT_COURSES_MENU:
                        loop = false;
                        break;
                    case GET_COURSE_DETAILS:
                        //courseManager.getCourseDetails();
                        break;
                    case GET_ALL_COURSES:
                        //courseManager.getAllCourses();
                        break;
                }
            }
            catch(IllegalArgumentException iae)
            {
                System.out.println(Colours.RED+"Please enter a single int"+Colours.RESET);
            }
        }
    }

    private void printCoursesMenu()
    {
        System.out.println("\n Options to select:");
        for(int i = 0; i<CoursesMenu.values().length; i++){
            System.out.println("\t"+ Colours.BLUE + i + ". " + CoursesMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }

}



