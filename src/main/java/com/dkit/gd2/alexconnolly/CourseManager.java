package com.dkit.gd2.alexconnolly;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * CoursesManager
 * This software component Encapsulates the storage and management of
 * all courses available through the CAO system.
 * Only administrators would typically be allowed to update this data,
 * but other users can get a COPY of all the courses by calling getAllCourses().
 * The Web Client would need this data to display the course codes,
 * course titles and institutions to the student.
 */

public class CourseManager {

    private List<Course> courses;
    private static Scanner keyboard = new Scanner(System.in);


    public CourseManager() {
        this.courses = new LinkedList<>();
        // Hardcode some values to get started
        // load from text file "courses.dat" and populate coursesMap
    }

    public void loadCoursesFromFile() {
        try(Scanner coursesFile = new Scanner(new BufferedReader(new FileReader("courses.txt"))))
        {
            String input;
            while(coursesFile.hasNextLine())
            {
                input = coursesFile.nextLine();
                String[] data = input.split(",");
                String courseId = data[0];
                String level = data[1];
                String title = data[2];
                String institution = data[3];

                Course readInCourse = new Course(courseId, level, title, institution);
                this.courses.add(readInCourse);
            }
        }

        catch(FileNotFoundException fne)
        {
            System.out.println(Colours.PURPLE+"Could not load courses. If this is the first time running the program" +
                    " this could be fine." + Colours.RESET);
        }
    }

    public void saveCoursesToFile() {
        try(BufferedWriter coursesFile = new BufferedWriter(new FileWriter("courses.txt")))
        {
            for(Course course: courses)
            {
                coursesFile.write(course.getCourseId()+","+ course.getLevel()+ "," +course.getTitle()
                        + "," +course.getInstitution());
                coursesFile.write("\n");
            }
        }
        catch(IOException ioe)
        {
            System.out.println(Colours.RED+"Could not save the courses"+Colours.RESET);
        }
    }

    public void getCourse()
    {
        if(this.courses != null)
        {
            String idToFind = enterField("course ID to find");
            Course courseToFind = findCourse(idToFind);

            if(courseToFind != null)
            {
                System.out.println(courseToFind);
            }
            else
            {
                System.out.println(Colours.RED + "That course does not exist" + Colours.RESET);
            }
        }
    }

    public void getAllCourses()
    {
        if (this.courses != null)
        {
            for(Course course: courses)
            {
                System.out.println(course);
            }
        }
    }
//
    public void addCourse()
    {
        String courseId = loopUntilValidEntry("courseId");
        String level = loopUntilValidEntry("level");
        String title = loopUntilValidEntry("title");
        String institution = loopUntilValidEntry("institution");
        boolean alreadyExists = false;

        for(Course course : courses)
        {
            if(course.getCourseId()==(courseId)){
                System.out.println(Colours.RED+"This course already exists, course will not be added"+Colours.RESET);
                alreadyExists=true;
            }
        }

        if(alreadyExists==false)
        {
            Course addedCourse = new Course(courseId, level, title, institution);
            Course clonedCourse = new Course(addedCourse);

            this.courses.add(addedCourse);
            System.out.println(Colours.GREEN+"Course has been successfully added"+Colours.RESET);
        }
    }
//
    public void removeCourse(/*String courseID*/)
    {
        if(this.courses != null)
        {
            int idToRemove = Integer.parseInt(enterField("course ID to remove"));
            Course courseToRemove = findCourse(idToRemove);

            if(courseToRemove != null){
                courses.remove(courseToRemove);
            }
            else
            {
                System.out.println(Colours.RED + "That course does not exist" + Colours.RESET);
            }
        }
    }

    private String loopUntilValidEntry(String entryName)
    {
        boolean loop = true;
        while(loop)
        {
            if(entryName.equals("courseId"))
            {
                String courseId = enterField("courseId");

                if (courseId.matches("DK[0-9]{3}"))
                {
                    return courseId;
                }
                else
                {
                    System.out.println("course Id format invalid!");

                }
            }

            else if(entryName.equals("level"))
            {
                String level = enterField("level");

                if (level.matches("[5-8]{1}"))
                {
                    return level;
                }
                else
                {
                    System.out.println("Course level format invalid!");
                }
            }

            else if(entryName.equals("title"))
            {
                String title = enterField("title");

                if (title.matches("(.)+"))
                {
                    return title;
                }
                else
                {
                    System.out.println("Title format invalid");
                }
            }

            else if(entryName.equals("institution"))
            {
                String institution = enterField("institution");

                if (institution.matches("(.)+"))
                {
                    return institution;
                }
                else
                {
                    System.out.println("Institution format invalid!");

                }
            }
        }
        return null;
    }

    private String enterField(String field)
    {
        String input;
        System.out.print("Please enter course's " + field + ":>" );

        input = keyboard.nextLine();
        return input;
    }

    private Course findCourse(int idToFind)
    {
        for(Course course : courses)
        {
            if(course.getCourseId().equals(idToFind))
            {
                return course;
            }
        }
        return null;
    }

    private Course findCourse(String idToFind)
    {
        for(Course course : courses)
        {
            if(course.getCourseId().equals(idToFind))
            {
                return course;
            }
        }
        return null;
    }

    // editCourse(courseId);       // not required for this iteration

}

