package com.dkit.gd2.alexconnolly;

import java.util.Scanner;

/**
 *  Names: Alex Connolly & Rachel Daly
 *  ID: D00226250 & D00227638
 *
 */
public class Student
{
    private int caoNumber;  // In the CAO system, cao number is unique identifier for student
    private String dateOfBirth; // yyyy-mm-dd
    private String password;    // min 8 characters
    private String email;

    public Student (Student studentToCopy)
    {
        this.caoNumber = studentToCopy.getCaoNumber();
        this.dateOfBirth = studentToCopy.getDateOfBirth();
        this.password = studentToCopy.getPassword();
        this.email = studentToCopy.getEmail();
        //return new Student(this.caoNumber, this.dateOfBirth, this.password, this.email);
    }

    // Constructor
    protected Student(int caoNumber, String dateOfBirth, String password, String email)
    {
        this.caoNumber = caoNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
    }


    public int getCaoNumber()
    {
        return caoNumber;
    }

    private void setCaoNumber(int caoNumber)
    {
        this.caoNumber = caoNumber;
    }

    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    private void setDateOfBirth(String dayOfBirth)
    {
        this.dateOfBirth = dayOfBirth;
    }

    public String getPassword()
    {
        return password;
    }

    private void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    private void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "Student{" +
                "caoNumber=" + caoNumber +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
