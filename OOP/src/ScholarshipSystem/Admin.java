package ScholarshipSystem;

import java.util.*;

/**
 * Created by alice on 10/22/14.
 */

/*
 TODO: Potentially update call to Scholarship constructor when deadline stuff is figured out
 */

public class Admin {

	private String adminID;
	private String password;

	public Admin()
	{
		adminID = "";
		password = "";
	}
	public Admin(String id, String pw)
	{
		adminID = id;
		password = pw;
	}

	public void setAdminID(String id) { adminID = id; }
	public void setPassword(String pw) { password = pw; } // how does security work we just don't know

	public String getAdminID() { return adminID; }
	public String getPassword() { return password; } // ditto

	public void addScholarship()
	{
		// I/O for now

		Scanner stdin = new Scanner(System.in);

        System.out.println("Welcome!\n" +
                "Enter new scholarship details.");

		System.out.println("Scholarship name: ");
		String name = stdin.nextLine();

		System.out.println("Award amount: ");
		int amount = stdin.nextInt();

        System.out.println("Application deadline: ");
        String deadline = stdin.nextLine();

		System.out.println("Maximum number of awardees: ");
		int studentLimit = stdin.nextInt();

        System.out.println("Required major: "); // expand to multiple allowed majors in future iterations?
        String major = stdin.nextLine();

        System.out.println("Minimum required credit hours: ");
        int hours = stdin.nextInt();

        System.out.println("Minimum required GPA: ");
        double gpa = stdin.nextDouble();

        Scholarship new_scholarship = new Scholarship(amount, name, deadline, studentLimit, major, hours, gpa);

        System.out.println("Scholarship successfully created!");
        // print toString of new scholarship
	}

}
