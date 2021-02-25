package com.dkit.gd2.alexconnolly;

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
    boolean loginVerif = false;
    public static void main( String[] args )
    {
        System.out.println( "CAO Online - CA4" );
        new App() .start();
    }

    private void start() {


        StudentManager studentManager = new StudentManager();
        studentManager.loadStudentsFromFile();

        CourseManager courseManager= new CourseManager();
        courseManager.loadCoursesFromFile();


        // load manager to provide functionality to allow a student
        // to login and add/update their course selections
        // This CourseChoicesManager component depends on the
        // StudentManager and the CourseManager,
        // so we 'inject' or pass-in these objects.
        //
        CourseChoicesManager courseChoicesManager = new CourseChoicesManager(studentManager, courseManager);
        courseChoicesManager.loadStudentChoicesFromFile();
        //test runs for methods
        //StudentManager
//        System.out.println(studentManager.getStudent(78231230));
//        studentManager.removeStudent();

        courseManager.getAllCourses();


        // display a menu to do things
        loginVerif = loginVerification(courseChoicesManager, studentManager, courseManager);

        // manual testing of mgr public interface



        //mgr.saveToFile();
        studentManager.saveStudentsToFile();
        courseManager.saveCoursesToFile();
        courseChoicesManager.saveStudentChoicesToFile();
    }

    private boolean loginVerification(CourseChoicesManager courseChoicesManager, StudentManager studentManager,
                                        CourseManager courseManager)
    {
        boolean loop = true;
        while(loop)
        {
            loginVerif= studentManager.login();
            if (loginVerif == true)
            {
                doMainMenuLoop(studentManager, courseManager, courseChoicesManager);
                loop = false;
            }
        }
        return false;
    }

    private void doMainMenuLoop(StudentManager studentManager, CourseManager courseManager,
                                CourseChoicesManager courseChoicesManager)
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
                    case DISPLAY_STUDENT_MENU:
                        doStudentMenuLoop(courseChoicesManager, courseManager);
                        break;
                    case DISPLAY_ADMINISTRATOR_MENU:
                        doAdministratorMenuLoop(studentManager, courseManager);
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

    private void doStudentMenuLoop(CourseChoicesManager courseChoicesManager, CourseManager courseManager) {
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
                    case DISPLAY_COURSE_DETAILS:
                        courseManager.getCourse();
                        break;
                    case DISPLAY_ALL_COURSES:
                       courseManager.getAllCourses();
                        break;
                    case DISPLAY_STUDENT_CHOICES:
                        courseChoicesManager.getStudentChoices();
                        break;
                    case UPDATE_CHOICES:
                        courseChoicesManager.updateChoices();
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


    private void doAdministratorMenuLoop(StudentManager studentManager, CourseManager courseManager)
    {
        boolean loop = true;
        AdministratorMenu menuOption;
        int option;
        while(loop)
        {
            printAdministratorMenu();
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

                if(option <0 || option >= AdministratorMenu.values().length)
                {
                    throw new IllegalArgumentException();
                }
                menuOption = AdministratorMenu.values()[option];
                switch(menuOption)
                {
                    case  QUIT_ADMINISTRATOR_MENU:
                        loop = false;
                        break;
                    case ADD_COURSE:
                        courseManager.addCourse();
                        break;
                    case REMOVE_COURSE:
                        courseManager.removeCourse();
                        break;
                    case DISPLAY_ALL_COURSES:
                        courseManager.getAllCourses();
                        break;
                    case DISPLAY_COURSE_DETAILS:
                        courseManager.getCourse();
                        break;
                    case ADD_STUDENT:
                        studentManager.addStudent();
                        break;
                    case REMOVE_STUDENT:
                        studentManager.removeStudent();
                        break;
                    case DISPLAY_STUDENT:
                        studentManager.getStudent();
                        break;
                }
            }
            catch(IllegalArgumentException iae)
            {
                System.out.println(Colours.RED+"Please enter a single int"+Colours.RESET);
            }
        }
    }

    private void printAdministratorMenu()
    {
        System.out.println("\n Options to select:");
        for(int i = 0; i< AdministratorMenu.values().length; i++){
            System.out.println("\t"+ Colours.BLUE + i + ". " + AdministratorMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }

}



