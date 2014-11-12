package OOPbox;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by alice on 11/8/14.
 */
public class Driver {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        System.out.println("Welcome!\n" +
                "Student or admin?");

        String response = stdin.nextLine();

        while(!(response.toLowerCase().equals("student") || response.toLowerCase().equals("admin"))) {
            System.out.println("Enter 'student' or 'admin' --> ");
            response = stdin.nextLine();
        }

        if(response.toLowerCase().equals("student"))
        {
            System.out.println("Enter Student ID: ");
            String student_id = stdin.nextLine();

            // authentication!
//            if(Student.authenticate(student_id)) {
                System.out.println("What is your major? ");
                // with gui we can use a dropdown for this; as it is when we make up some test scholarships we'll end up
                // with a list of corresponding majors and we can display that
                String major = stdin.nextLine();
                System.out.println("How many credit hours have you completed prior to the current semester? ");
                int hours = stdin.nextInt();
                System.out.println("What is your GPA? (Out of 4.0) ");
                double gpa = stdin.nextDouble();

                Student applicant = new Student(student_id, major, hours, gpa);
                System.out.println("Searching for scholarships...");
                try
                {
                    /*
                    Call method eligibleForScholarships() of Student class.
                    Method checks each scholarship file for student eligibility.
                    Matches are printed as they are found and associated with a number.
                        If no matches are found, method will print "There are no scholarship matches."
                    Method asks student to indicate their desired scholarship, opens associated recipients file, and appends the student's ID.
                     */
                    applicant.eligibleForScholarships();
                }
                catch(FileNotFoundException e)
                {
                    System.out.println("There are no scholarships available at this time.");
                }

//            }
//            else // Student not authenticated
//            {
//                // TODO: message dialogue and exit
//            }
        }
        else // Admin
        {
            System.out.println("Enter ID: ");
            String admin_id = stdin.nextLine();
//
//            if(Admin.authenticate(admin_id)) {
                Admin admin = new Admin(admin_id);
            System.out.println("What would you like to do?" +
                    "\nA - (A)dd a scholarship" +
                    "\nR - See (r)ecipients of a scholarship" +
                    "\nS - See scholarship awarded to a (s)tudent");
            String response2 = stdin.nextLine();
            while(!(response2.toLowerCase().equals("a") || response2.toLowerCase().equals("r") || response2.toLowerCase().equals("s")))
            {
                System.out.println("Please enter one of: A to add scholarship, R to see recipients, S to see student scholarship.");
                response2 = stdin.nextLine();
            }

            if(response2.toLowerCase().equals("a"))
            {
                admin.addScholarship();
            }
            else if(response2.toLowerCase().equals("r"))
            {
                admin.printRecipients();
            }
            else if(response2.toLowerCase().equals("s"))
            {
                // print student scholarship
            }


//            }
//            else
//            {
                // TODO: message dialogue and exit
//            }
        }

    }
}