package com.dkit.gd2.alexconnolly;

import java.util.Scanner;

public class Student
{
    private int caoNumber;  // In the CAO system, cao number is unique identifier for student
    private String dateOfBirth; // yyyy-mm-dd
    private String password;    // min 8 characters
    private String email;

    public Student copyStudent(Student studentToCopy)
    {
        this.caoNumber = studentToCopy.getCaoNumber();
        this.dateOfBirth = studentToCopy.getDateOfBirth();
        this.password = studentToCopy.getPassword();
        this.email = studentToCopy.getEmail();
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
        if(dateOfBirth != null)
        {
            //4 digits for year, starting with 20, 2 digits for month, firs one can't go over 1 (12 months), 2 digits for days, up to 31
            if(!dateOfBirth.matches("^20{1}\\d{2}/[0-1]{1}\\d{1}/[0-3]{1}\\d{1}"))
            {
                System.out.println("Invalid birth date format");
                return false;
            }
            else
            {
                if (this.dateOfBirth.equals(dateOfBirth))
                {
                    return true;
                }
                else if ((!this.dateOfBirth.equals(dateOfBirth)))
                {
                    return false;
                }
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
                 if (this.password.equals(password))
                 {
                     return true;
                 }
                 else if (!this.password.equals(password))
                 {
                     return false;
                 }
            }
            else if(password.length() < 0 || password.length() > 16)
            {
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
            if(caoNum.length() == 8)
            {
                if(caoNum.contains("[0-9]"))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else if(caoNum.length() > 8 || caoNum.length() < 8)
            {
                return false;
            }
        }
        else
        {
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
