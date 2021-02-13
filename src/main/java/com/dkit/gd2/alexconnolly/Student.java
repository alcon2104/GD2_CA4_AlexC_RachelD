package com.dkit.gd2.alexconnolly;

public class Student
{
    private int caoNumber;  // In the CAO system, cao number is unique identifier for student
    private String dateOfBirth; // yyyy-mm-dd
    private String password;    // min 8 characters
    private String email;

    public Student copyStudent(Student student)
    {
        this.caoNumber = student.getCaoNumber();
        this.dateOfBirth = student.getDateOfBirth();
        this.password = student.getPassword();
        this.email = student.getEmail();
        return new Student(this.caoNumber, this.dateOfBirth, this.password, this.email);
    }

    // Constructor
    public Student(int caoNumber, String dateOfBirth, String password, String email)
    {
        this.caoNumber = caoNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
    }

    public boolean verifyLoginCredentials(String dateOfBirth, String password)
    {
        if(dateOfBirth != null && password != null)
        {
            //4 digits for year, starting with 20, 2 digits for month, firs one can't go over 1 (12 months), 2 digits for days, up to 31
            if(!dateOfBirth.matches("^20{1}\\d{2}/[0-1]{1}\\d{1}/[0-3]{1}\\d{1}"))
            {
                System.out.println("Invalid birth date format");
                return false;
            }
            else
            {
                if (this.dateOfBirth.equals(dateOfBirth) && this.password.equals(password))
                {
                    System.out.println("Access Granted");
                    return true;
                }
                else if ((!this.dateOfBirth.equals(dateOfBirth) && !this.password.equals(password))
                        || (!this.dateOfBirth.equals(dateOfBirth) || !this.password.equals(password)))
                {
                    System.out.println("One or both fields are incorrect - please enter valid credentials");
                    return false;
                }
            }
        }
        else if((dateOfBirth != null || password != null) || (dateOfBirth == null && password == null))
        {
            System.out.println("Date of Birth and/or password left blank - please fill in these details");
            return false;
        }
        return false;
    }

    public int getCaoNumber()
    {
        return caoNumber;
    }

    public void setCaoNumber(int caoNumber)
    {
        this.caoNumber = caoNumber;
    }

    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dayOfBirth)
    {
        this.dateOfBirth = dayOfBirth;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
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
