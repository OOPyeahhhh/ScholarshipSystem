package ScholarshipSystem;

/**
 * Created by alice on 10/22/14.
 */

/*
TODO: Overload toString()
TODO: Overload equals ?
 */

public class Student {

	private int studentID;
	private String password;
	private Criteria studentCrit;

	public Student()
	{
		studentID = -1;
		password = "";
		studentCrit = null;
	}
	public Student(int id, String pw, String major, int hours, double gpa)
	{
		studentID = id;
		password = pw;
		//studentCrit = new Criteria(major, hours, gpa); // need Criteria constructor
	}

}
