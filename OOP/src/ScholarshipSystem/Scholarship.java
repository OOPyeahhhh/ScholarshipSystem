package ScholarshipSystem;
import java.util.*;
import java.text.*;
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
	private	String deadline; //in MM/dd/yyyy
	private String scholarshipName;
	private Criteria scholarship_criteria;
	private int counter;
	//database for students?  

	public Scholarship()
	{
		awardAmount = 0;
		scholarshipName = "";
		totalNumOfAwardees  = 0;
		deadline = "01/01/2000";
		counter = 0;
	}

	public Scholarship(int amount, String name, String d, int limit, String major, int hours, double gpa)
	{
		scholarshipName = name;
		awardAmount = amount;
		deadline = d;
		totalNumOfAwardees = limit;
        //scholarship_criteria = new Criteria(major, hours, gpa); // need Criteria constructor
		counter = 0;
	}

	public boolean isFull()
	{
		return (counter == totalNumOfAwardees);
	}

	public Boolean onTime()
	{
		DateFormat dateFormat = new SimpleDate("MM/dd/yyyy"); //in MM/dd/yyyy
		Date date = new Date();
		Date todayDate = dateFormat.format(date);
		Date deadlineDate = dateFormat.format(deadline);

		return todayDate.before(deadline);
	}

	public boolean isEligible(Student stu)
	{
		return (onTime() && !isFull() && stu.crit==scholarship_criteria); //overloaded = operator for criteria
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
