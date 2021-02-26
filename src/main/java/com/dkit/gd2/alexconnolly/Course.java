package com.dkit.gd2.alexconnolly;
/**
 *  Names: Alex Connolly & Rachel Daly
 *  ID: D00226250 & D00227638
 *
 */
public class Course {

    private String courseId;   // e.g. DK821
    private String level;      // e.g. 7, 8, 9, 10
    private String title;      // e.g. BSc in Computing in Software Development
    private String institution; // Dundalk Institute of Technology

    // Copy Constructor
    public Course (Course courseToCopy)
    {
        this.courseId = courseToCopy.getCourseId();
        this.level =  courseToCopy.getLevel();
        this.title =  courseToCopy.getTitle();
        this.institution =  courseToCopy.getInstitution();
        //return new Course(this.courseId, this.level, this.title, this.institution);
    }
    // Accepts a Course object as an argument and copies all the field values
    // into a new Course object. Returns the new cloned object.
    // (add here)


    // Constructor
    protected Course(String courseId, String level,String title, String institution) {
        this.courseId = courseId;
        this.level = level;
        this.title = title;
        this.institution = institution;
    }



    public String getCourseId() {
        return courseId;
    }
    private void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getLevel() {
        return level;
    }
    private void setLevel(String level) {
        this.level = level;
    }
    public String getInstitution() {
        return institution;
    }
    private void setInstitution(String institution) {
        this.institution = institution;
    }
    public String getTitle() { return title;  }
    private void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", level='" + level + '\'' +
                ", institution='" + institution + '\'' +
                '}';
    }
}
