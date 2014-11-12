package OOPbox;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;

/**
 * Created by alice on 11/8/14.
 */
/*
TODO: Overload toString()
TODO: Overload equals ?
 */

public class Student {

    private String studentID;
    protected Criteria student_criteria;

    public Student()
    {
        studentID = "";
        student_criteria = null;
    }
    public Student(String id, String major, int hours, double gpa)
    {
        studentID = id;
        student_criteria = new Criteria(major, hours, gpa);
    }


    public void eligibleForScholarships() throws FileNotFoundException
    {
        String myDirectoryPath = "/Users/alice/Desktop/IdeaProjects/OOP sandbox/src/OOPbox/Scholarships";
        File dir = new File(myDirectoryPath);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            boolean atLeastOne = false;
            // TODO: skip first file or delete it
            for (File child : directoryListing) // Search for and print matching scholarships if any
            {
                Scanner scholarshipFile = new Scanner(child); // Throws FileNotFoundException if there are no files in the directory (I think). Means there are no scholarships. Driver prints message.

                // Get ID number of scholarship
                int scholarshipID = scholarshipFile.nextInt();
                scholarshipFile.nextLine(); // Read in \n

                // Get scholarship name
                String name = scholarshipFile.nextLine();

                // Get scholarship deadline
                Calendar deadline = Calendar.getInstance();
                // Calendar counts months from 0
                int year = scholarshipFile.nextInt();
//                scholarshipFile.next();
                int month = scholarshipFile.nextInt()-1;
//                scholarshipFile.next();
                int day = scholarshipFile.nextInt();
                deadline.set(year, month, day);
                if(Calendar.getInstance().before(deadline)) // Today is before deadline
                {
                    // Get maximum number of awardees
                    int awardeeLimit = scholarshipFile.nextInt();
                    scholarshipFile.nextLine(); // read \n

                    // Get number of current recipients
                    String rFileName = "/Users/alice/Desktop/IdeaProjects/OOP sandbox/src/OOPbox/Recipients/recipients" + scholarshipID + ".txt";

                    Scanner temp = new Scanner(new File(rFileName));
                    int numberOfLines = 0;
                    while(temp.hasNextLine())
                    {
                        temp.nextLine(); // read line
                        numberOfLines++;
                    }
                    int numberOfRecipients = numberOfLines;
                    temp.close();

                    // See if recipient limit reached
                    if (numberOfRecipients < awardeeLimit) // Scholarship can still be awarded
                    {
                        // Check if student has previously been awarded the scholarship
                        boolean newApplicant = true;
                        Scanner temp2 = new Scanner(new File(rFileName));
                        while(temp2.hasNextLine() && newApplicant) {
                            String recipient = temp2.nextLine();
                            if (studentID.equals(recipient))
                                newApplicant = false;
                        }
                        temp2.close();

                        if (newApplicant) // If student is new applicant
                        {
                            // Check criteria
                            String major = scholarshipFile.nextLine();
                            int hours = scholarshipFile.nextInt();
                            scholarshipFile.nextLine(); // read \n
                            double gpa = scholarshipFile.nextDouble();
                            scholarshipFile.nextLine(); // read \n
                            if (student_criteria.getMajor().equals(major) && student_criteria.getHours() >= hours && student_criteria.getGpa() >= gpa)
                            {
                                if(!atLeastOne) {
                                    atLeastOne = true;
                                    System.out.println("You are eligible for the following scholarship(s): ");
                                }

                                // Get award amount
                                double award = scholarshipFile.nextDouble();

                                // Print scholarship details in format
                                // Title:        X Scholarship    ID#: 1
                                // Award amount: $500
                                System.out.println("Title:        " + name + "\tID#: " + scholarshipID + "\nAward amount: $" + award);
                            }
                            else // Student is not eligible
                            {
                                //break;
                            }
                        }
                        else // Student is already recipient of the scholarship
                        {
                            break;
                        }
                    }
                    else // Scholarship full
                    {
                        break;
                    }
                }
                else // Today is after deadline
                {
                    break;
                }

                // Read \n at end of file
                scholarshipFile.nextLine();

                scholarshipFile.close();

            }

            if(!atLeastOne) // Student is not eligible for any scholarships
            {
                System.out.println("There are no scholarship matches.");
            }
            else // Student found to be eligible for at least 1 scholarship, which was/were printed
            {
                Scanner stdin = new Scanner(System.in);
                System.out.println("Please choose a scholarship by entering its ID number.");
                int response = stdin.nextInt(); // TODO: validate this input

                String recipientsFileName = "/Users/alice/Desktop/IdeaProjects/OOP sandbox/src/OOPbox/Recipients/recipients" + response + ".txt";
                try
                {
                    FileWriter recipientsFile = new FileWriter(new File(recipientsFileName), true);
                    recipientsFile.write(studentID);
                    recipientsFile.write("\n");
                    recipientsFile.close();
                    System.out.println("You have been added to the list of scholarship recipients.");

                }
                catch(IOException e)
                {
                    System.out.println("An error occurred while adding to recipients list.");
                }

            }
        }
        else
        {
            System.out.println("Error: not a directory.");
        }
    }
    public static boolean authenticate(String student_id)
    {
        boolean authenticated = true;

        // search among students for ID == student_id, thereby finding correct password
        // TODO: if profile not found, student not in system. use case/domain rule(?) thing; we are assuming only students who are in the system can use this.
        // having found the profile, set a variable for password comparison

        char[] correctPassword = { 'a', 'l', 'l', 'i', 's', 'o', 'n' };

        // TODO: Instantiate JPasswordField
        char[] input = new char[0];

        if(input.length != correctPassword.length)
            authenticated = false;
        else
            authenticated = Arrays.equals(input, correctPassword);

        // Zero out the password
        Arrays.fill(correctPassword, '0');

        return authenticated;
    }


}

