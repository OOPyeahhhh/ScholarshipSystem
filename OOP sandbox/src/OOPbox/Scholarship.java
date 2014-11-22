package OOPbox;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Calendar;
import java.io.File;

/**
 * Created by alice on 11/8/14.
 */
/*
TODO: Overload toString (need it in Admin to confirm addition of scholarship)
 */

public class Scholarship
{
    private int awardAmount;
    private int totalNumOfAwardees;
    private	Date deadline; // TODO: Do we need this?
    Calendar dead = Calendar.getInstance();
    private String scholarshipName;
    private Criteria scholarship_criteria;
    private int scholarshipID;

    public Scholarship()
    {
        awardAmount = 0;
        scholarshipName = "";
        totalNumOfAwardees  = 0;
        dead.set(2000,0,1); //yyyy,mm,dd
        deadline = dead.getTime();
        setID();
        // TODO: What happens if such default scholarship is created? Does it need to be written to a file?
    }

    public Scholarship(int amount, String name, int year, int month, int day, int limit, String major, int hours, double gpa)
    {
        scholarshipName = name;
        awardAmount = amount;
        dead.set(year,month-1,day);
        deadline = dead.getTime();
        totalNumOfAwardees = limit;
        scholarship_criteria = new Criteria(major, hours, gpa);
        setID();
        writeScholarship();
    }

    private void setID()
    {
        String schDirPath = "/Users/alice/ScholarshipSystem/OOP sandbox/src/OOPbox/Scholarships";
        scholarshipID = new File(schDirPath).list().length + 1;
    }
    private void writeScholarship()
    {
        // Create file with name "scholarship[ID#].txt"
        String scholarshipFileName = "/Users/alice/ScholarshipSystem/OOP sandbox/src/OOPbox/Scholarships/scholarship" + scholarshipID + ".txt";
        try {
            PrintWriter scholarshipFile = new PrintWriter(new File(scholarshipFileName));
            /* Write to file with format

        1               // ID #
        X Scholarship   // name
        2000 1 1        // 2000 January 1
        30              // recipients limit
        Mathematics     // required major
        40              // required hours
        3.0             // required gpa
        1000            // award amount in $
         */

            scholarshipFile.println(scholarshipID);
            scholarshipFile.println(scholarshipName);
            int month = dead.get(Calendar.MONTH) + 1; // Calendar counts months from 0
            scholarshipFile.println(dead.get(Calendar.YEAR) + " " + month + " " + dead.get(Calendar.DAY_OF_MONTH)); // Uses the calendar object dead
            scholarshipFile.println(totalNumOfAwardees);
            scholarshipFile.println(scholarship_criteria.getMajor());
            scholarshipFile.println(scholarship_criteria.getHours());
            scholarshipFile.println(scholarship_criteria.getGpa());
            scholarshipFile.println(awardAmount);

            scholarshipFile.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error creating scholarship file.");
        }

        // Create empty recipients file
        String recipientsFileName = "/Users/alice/ScholarshipSystem/OOP sandbox/src/OOPbox/Recipients/recipients" + scholarshipID + ".txt";
        try {
            PrintWriter recipientsFile = new PrintWriter(new File(recipientsFileName));
            recipientsFile.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error creating recipients file.");
        }
    }

    public void setAwardAmount(int num)
    {
        awardAmount = num;
    }

    public void setTotalNumOfAwardees(int num)
    {
        totalNumOfAwardees = num;
    }

    public void setScholarshipName(String s)
    {
        scholarshipName = s;
    }

    public void setDeadline(Date dead)
    {
        deadline = dead;
    }

    public int getAwardAmount()
    {
        return awardAmount;
    }

    public int getTotalNumOfAwardees()
    {
        return totalNumOfAwardees;
    }

    public String getScholarshipName()
    {
        return scholarshipName;
    }

    public Date getDeadline()
    {
        return deadline;
    }
}

