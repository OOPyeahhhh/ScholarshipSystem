package ScholarshipSystem;
import java.util.Date;
import java.util.Calendar;
/**
 * Created by alice on 10/22/14.
 */

/*
TODO: Overload toString (need it in Admin to confirm addition of scholarship)
 */

public class Scholarship
{
    private int awardAmount;
    private int totalNumOfAwardees;
    private	Date deadline;
    Calander dead = Calendar.getInstance();
    private String scholarshipName;
    private Criteria scholarship_criteria;
    private int counter;
    //database for students?

    public Scholarship()
    {
        awardAmount = 0;
        scholarshipName = "";
        totalNumOfAwardees  = 0;
        dead.set(2000,1,1); //yyyy,mm,dd
        deadline = dead.getTime();
        counter = 0;
    }

    public Scholarship(int amount, String name, int year, int month, int day, int limit, String major, int hours, double gpa)
    {
        scholarshipName = name;
        awardAmount = amount;
        dead.set(year,month,day);
        deadline = dead.getTime();
        totalNumOfAwardees = limit;
        scholarship_criteria = new Criteria(major, hours, gpa);
        counter = 0;
    }

    public boolean isFull()
    {
        return (counter == totalNumOfAwardees);
    }

    public Boolean onTime()
    {
        Date todayDate = new Date(); //Current Date
        return todayDate.before(deadline);
    }

    public boolean isEligible(Student stu)
    {
        return (onTime() && !isFull() && stu.student_criteria==scholarship_criteria); //overloaded = operator for criteria
    }

    public boolean newApplicant()
    {
        //database queery
        counter++;
    }

    public void display()
    {
        //database
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
