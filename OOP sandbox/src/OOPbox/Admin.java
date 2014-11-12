package OOPbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by alice on 11/8/14.
 */
/*
 TODO: Potentially update call to Scholarship constructor when deadline stuff is figured out
 */

public class Admin {

    private String adminID;

    public Admin()
    {
        adminID = "";
    }
    public Admin(String id)
    {
        adminID = id;
    }

    public void setAdminID(String id) { adminID = id; }

    public String getAdminID() { return adminID; }

    public static boolean authenticate(String admin_id)
    {
        boolean authenticated = true;

        // search among admins for ID == admin_id, thereby finding correct password
        // having found the profile, set a variable for password comparison

        char[] correctPassword = { 'a', 'l', 'l', 'i', 's', 'o', 'n' };

        // instantiate JPasswordField
        char[] input = new char[0];

        if(input.length != correctPassword.length)
            authenticated = false;
        else
            authenticated = Arrays.equals(input, correctPassword);

        // zero out the password
        Arrays.fill(correctPassword, '0');

        return authenticated;
    }
    public void addScholarship()
    {

        Scanner stdin = new Scanner(System.in);

        System.out.println("Welcome!\n" +
                "Enter new scholarship details.");

        System.out.println("Scholarship name: ");
        String name = stdin.nextLine();

        System.out.println("Award amount: ");
        int amount = stdin.nextInt();

        // just as filler to edit Scholarship constructor call
        System.out.println("Application deadline year: ");
        int dly = stdin.nextInt();

        System.out.println("Application deadline month: ");
        int dlm = stdin.nextInt();

        System.out.println("Application deadline day: ");
        int dld = stdin.nextInt();

        System.out.println("Maximum number of awardees: ");
        int studentLimit = stdin.nextInt();
        stdin.nextLine(); // read in \n

        System.out.println("Required major: "); // expand to multiple allowed majors in future iterations?
        String major = stdin.nextLine();

        System.out.println("Minimum required credit hours: ");
        int hours = stdin.nextInt();

        System.out.println("Minimum required GPA: ");
        double gpa = stdin.nextDouble();

        Scholarship new_scholarship = new Scholarship(amount, name, dly, dlm, dld, studentLimit, major, hours, gpa);

        System.out.println("Scholarship successfully created!");
        // print toString of new scholarship
    }
    public void printRecipients() // As required in project description
    {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Enter ID number of scholarship.");
//        if(stdin.hasNextInt())
//        {
        boolean invalid = false;
        do {
            while (!stdin.hasNextInt()) {
                System.out.println("Please check that your input is a number.");
            }

            int scholarshipID = stdin.nextInt();
            String fileName = "/Users/alice/Desktop/IdeaProjects/OOP sandbox/src/OOPbox/Recipients/recipients" + scholarshipID + ".txt";

            try {
                Scanner recipientsFile = new Scanner(new File(fileName));
                System.out.println("The scholarship recipients are: ");
                while(recipientsFile.hasNextLine()) {
                    System.out.println(recipientsFile.nextLine());
                }
                recipientsFile.close();
            } catch (FileNotFoundException e) {
                System.out.println("Please enter a valid scholarship ID number.");
                invalid = true;
            }
        } while (invalid);
        stdin.close();
//        }
//        else if(stdin.hasNextLine())
//        {
//            String scholarshipName = stdin.nextLine();
//        }

    }

}