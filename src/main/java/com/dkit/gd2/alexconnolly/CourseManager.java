package com.dkit.gd2.alexconnolly;

import java.io.*;
import java.util.*;

/**
 * CoursesManager
 * This software component Encapsulates the storage and management of
 * all courses available through the CAO system.
 * Only administrators would typically be allowed to update this data,
 * but other users can get a COPY of all the courses by calling getAllCourses().
 * The Web Client would need this data to display the course codes,
 * course titles and institutions to the student.
 */

/**
 *  Names: Alex Connolly & Rachel Daly
 *  ID: D00226250 & D00227638
 *
 */
public class CourseManager {

    private Map<String, Course> courses;
    private static Scanner keyboard = new Scanner(System.in);


    protected CourseManager() {
        this.courses = new HashMap<>();
        // Hardcode some values to get started
        // load from text file "courses.dat" and populate coursesMap
    }

    protected void loadCoursesFromFile() {
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
                this.courses.put(courseId,readInCourse);
            }
        }

        catch(FileNotFoundException fne)
        {
            System.out.println(Colours.PURPLE+"Could not load courses. If this is the first time running the program" +
                    " this could be fine." + Colours.RESET);
        }
    }

    protected void saveCoursesToFile() {
        try(BufferedWriter coursesFile = new BufferedWriter(new FileWriter("courses.txt")))
        {
            for(Map.Entry<String, Course> course : courses.entrySet())
            {
                coursesFile.write(course.getValue().getCourseId()+","+ course.getValue().getLevel()
                                    + "," +course.getValue().getTitle() + "," +course.getValue().getInstitution());
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
        for(String key : courses.keySet())
        {
            System.out.println(courses.get(key));
        }
    }

    protected void addCourse()
    {
        String courseId = loopUntilValidEntry("courseId");
        String level = loopUntilValidEntry("level");
        String title = loopUntilValidEntry("title");
        String institution = loopUntilValidEntry("institution");


        if(courses.keySet().contains(courseId))
        {
            System.out.println(Colours.RED+"This course already exists, course will not be added"+Colours.RESET);
        }

        else
        {
            Course addedCourse = new Course(courseId, level, title, institution);
            Course clonedCourse = new Course(addedCourse);

            this.courses.put(courseId,addedCourse);
            System.out.println(Colours.GREEN+"Course has been successfully added"+Colours.RESET);
        }
    }

    protected void removeCourse()
    {
        if(this.courses != null)
        {
            String idToRemove = enterField("course ID to remove");
            Course courseToRemove = findCourse(idToRemove);

            if(courseToRemove != null){
                courses.remove(courseToRemove.getCourseId());
                System.out.println(Colours.GREEN + "Course removed successfully" + Colours.RESET);
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

                if (courseId.matches("^[A-Z]{2}[0-9]{3}"))
                {
                    return courseId;
                }
                else
                {
                    System.out.println(Colours.RED +"Course Id format invalid!"+ Colours.RESET);

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
                    System.out.println(Colours.RED +"Course level format invalid!"+ Colours.RESET);
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
                    System.out.println(Colours.RED +"Title format invalid"+ Colours.RESET);
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
                    System.out.println(Colours.RED +"Institution format invalid!"+ Colours.RESET);

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

    private Course findCourse(String idToFind)
    {
        if(courses.keySet().contains(idToFind))
        {
            return courses.get(idToFind);
        }

        return null;
    }

    // editCourse(courseId);       // not required for this iteration

}

