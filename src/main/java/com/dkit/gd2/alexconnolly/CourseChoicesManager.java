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

import java.io.*;
import java.util.*;

public class CourseChoicesManager
{
    private static Scanner keyboard = new Scanner(System.in);
    // reference to constructor injected studentManager
    private StudentManager studentManager;

    // reference to constructor injected courseManager
    private CourseManager courseManager;

    // Store all the Course details -  fast access
    private List<Course> choices;
    // caoNumber, course selection list - for fast access
    private Map<Integer, ArrayList> studentChoices;

    // CourseChoicesManager DEPENDS on both the StudentManager and CourseManager to access
    // student details and course details.  So, we receive a reference to each via
    // the constructor.
    // This is called "Dependency Injection", meaning that we
    // inject (or pass in) objects that this class requires to do its job.
    //
    CourseChoicesManager(StudentManager studentManager, CourseManager courseManager)
    {
        this.studentManager = studentManager;
        this.courseManager = courseManager;
        this.studentChoices = new HashMap<>();
    }

    public void loadStudentChoicesFromFile() {
        try(Scanner studentChoicesFile = new Scanner(new BufferedReader(new FileReader("studentChoices.txt"))))
        {
            String input;
            while(studentChoicesFile.hasNextLine())
            {
                input = studentChoicesFile.nextLine();
                String[] data = input.split(",");
                int caoNum = Integer.parseInt(data[0]);

                ArrayList<String> studentCourses = readStudentCourses(data);
                this.studentChoices.put(caoNum, studentCourses);
            }
        }

        catch(FileNotFoundException fne)
        {
            System.out.println(Colours.PURPLE+"Could not load students. If this is the first time running the program" +
                    " this could be fine." + Colours.RESET);
        }
    }

    private ArrayList<String> readStudentCourses(String[] data)
    {
        ArrayList<String> studentCourses = new ArrayList<>();
        for(int i = 1; i < data.length; i ++)
        {
            studentCourses.add(data[i]);
        }
        return studentCourses;
    }

    public void saveStudentChoicesToFile() {
        try(BufferedWriter studentChoicesFile = new BufferedWriter(new FileWriter("studentChoices.txt")))
        {
            for(Map.Entry<Integer, ArrayList> studentChoice : studentChoices.entrySet())
            {
                studentChoicesFile.write(studentChoice.getKey()+","+ studentChoice.getValue());
                studentChoicesFile.write("\n");
            }
        }
        catch(IOException ioe)
        {
            System.out.println(Colours.RED+"Could not save the students"+Colours.RESET);
        }
    }


    public void getStudentDetails()
    {
        studentManager.getStudent();
    }

    public void getCourseDetails()
    {
        courseManager.getCourse();
    }

    public void getStudentChoices()
    {
        int caoNumber = Integer.parseInt(loopUntilValidEntry("caoNumber"));

        if(caoNumber!=0)
        {
            System.out.println("why");
            findStudentWithChoices(caoNumber);
        }
    }

    private String loopUntilValidEntry(String caoNumber)
    {
        boolean loop = true;
        while(loop)
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
        return null;
    }


    void updateChoices()
    {

    }

    public void getAllCourses()
    {
        courseManager.getAllCourses();
    }

    private String enterField(String field)
    {
        String input;
        System.out.print("Please enter student's " + field + ":>" );

        input = keyboard.nextLine();
        return input;
    }

    private List<String> findStudentWithChoices(int caoToFind)
    {
        if(studentChoices.keySet().contains(caoToFind))
        {
            System.out.println("lifes a dinner");
            return this.studentChoices.get(caoToFind);
        }
        return null;
    }

    @Override
    public String toString() {
        return "CourseChoicesManager{" +
                "studentManager=" + studentManager +
                ", courseManager=" + courseManager +
                ", choices=" + choices +
                ", studentChoices=" + studentChoices +
                '}';
    }
}
