package ScholarshipSystem;
import java.util.*;
/**
 * Created by alice on 11/1/14.
 */
 
 // TODO: Change I/O to GUI
 
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
            /* THIS IS OBVIOUSLY NOT SECURE. WE ARE NOT THIS PERSON: http://www.bash.org/?244321
            System.out.println("Enter password: ");
            String student_pw = stdin.nextLine();
             */
            String student_pw = "";

            // authentication (aka magic)
            // if we go with that incredibly insecure password thing I guess we check it against a file/db of all students

            // IF AUTHENTICATED:
            System.out.println("What is your major? ");
            // with gui we can use a dropdown for this; as it is when we make up some test scholarships we'll end up
            // with a list of corresponding majors and we can display that
            String major = stdin.nextLine();
            System.out.println("How many credit hours have you completed prior to the current semester? ");
            int hours = stdin.nextInt();
            System.out.println("What is your GPA? (Out of 4.0) ");
            double gpa = stdin.nextDouble();

            Student applicant = new Student(student_id, student_pw, major, hours, gpa);

            // search for scholarships the student is eligible for (matching criteria, before deadline, under award limit)
            /*
               if none
                    display "Sorry, you're not eligible for anything"
               else {
                    display "You are eligible for these scholarships: "
                    // display the scholarships
                    "Choose the scholarship you wish to be awarded."
                    // something
                    "Congratulations or whatever"
                    // add student name to list of scholarship recipients
                    }
             */

            // else (if NOT authenticated)
            //      notify
        }
        else // admin
        {
            System.out.println("Enter ID: ");
            String admin_id = stdin.nextLine();
            /* here we go again
            System.out.println("Enter password: ");
            String admin_pw = stdin.nextLine();
             */
            String admin_pw = "";

            // authentication/magic

            // IF AUTHENTICATED
            Admin admin = new Admin(admin_id, admin_pw);
            admin.addScholarship();
        }

    }
}
