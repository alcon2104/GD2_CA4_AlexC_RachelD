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

    public Course getCourse(String courseID)
    {
        boolean courseExists = false;

        if(courses!=null)
        {
            for (Course course : courses)
            {
                if (course.getCourseId() == courseID)
                {
                    Course clonedCourse = new Course(course);
                    courseExists=true;
                    return clonedCourse;
                }
            }

            if (!courseExists)
            {
                System.out.println(Colours.RED + "Sorry, a course with this course ID does not exist." + Colours.RESET);
            }
        }
        return null;
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
//
//    public Course addCourse(String courseID)
//    {
        //take in course details from keyboard
        //return a copy of the course to the map
//    }
//
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

    // editCourse(courseId);       // not required for this iteration

}

