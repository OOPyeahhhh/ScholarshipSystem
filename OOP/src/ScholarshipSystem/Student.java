package ScholarshipSystem;

/**
 * Created by alice on 10/22/14.
 */

/*
TODO: Overload toString()
TODO: Overload equals ?
 */

public class Student {

	private String studentID;
	private String password;
	protected Criteria student_criteria;

	public Student()
	{
		studentID = "";
		password = "";
		student_criteria = null;
	}
	public Student(String id, String pw, String major, int hours, double gpa)
	{
		studentID = id;
		password = pw;
		//student_criteria = new Criteria(major, hours, gpa); // need Criteria constructor
	}

}
