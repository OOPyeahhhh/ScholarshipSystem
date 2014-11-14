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

    private int studentID;
    private boolean isScholarshipRecipient;
    protected Criteria student_criteria;

    public Student()
    {
        studentID = -1;
        student_criteria = new Criteria();
        initIsScholarshipRecipient();
    }
    public Student(int id, String major, int hours, double gpa)
    {
        studentID = id;
        initIsScholarshipRecipient();
        student_criteria = new Criteria(major, hours, gpa);
    }
    public Student(int id)
    {
        studentID = id;
        initIsScholarshipRecipient();
        student_criteria = new Criteria();
    }

    private void initIsScholarshipRecipient()
    {
        if(!isInSystem())
        {
            isScholarshipRecipient = false;
        }
        else
        {
            String fileName = "/Users/alice/ScholarshipSystem/OOP sandbox/src/OOPbox/Students/student" + studentID + ".txt";
            try {
                Scanner file = new Scanner(new File(fileName));
                file.nextInt();
                int status = file.nextInt();
                isScholarshipRecipient = (status!=0);
                file.close();

            } catch (FileNotFoundException e) {
                System.out.println("There was an error reading student file.");
            }
        }
    }
    public void setCriteria(String major, int hours, double gpa) {
        student_criteria.setMajor(major);
        student_criteria.setHours(hours);
        student_criteria.setGpa(gpa);
        // TODO: append to student file
        String studentFileName = "/Users/alice/ScholarshipSystem/OOP sandbox/src/OOPbox/Students/student"+studentID+".txt";
        try
        {
            FileWriter studentFile = new FileWriter(new File(studentFileName), true);
            studentFile.write(student_criteria.getMajor());
            studentFile.write("\n");
            studentFile.write(student_criteria.getHours());
            studentFile.write("\n");
            studentFile.write(String.valueOf(student_criteria.getGpa()));
            studentFile.write("\n");

            studentFile.close();
        }
        catch(IOException e)
        {
            System.out.println("An error occurred while updating student data.");
        }
    }

    public boolean isInSystem()
    {
        String studentFileName = "/Users/alice/ScholarshipSystem/OOP sandbox/src/OOPbox/Students/student"+studentID+".txt";
        return new File(studentFileName).isFile();
    }

    /**
     * Only called if !isScholarshipRecipient.
     * @throws FileNotFoundException
     */
    public void eligibleForScholarships() throws FileNotFoundException {
        if (isScholarshipRecipient) {
            System.out.println("Error, applicant is already receiving a scholarship.");

        } else {
            String myDirectoryPath = "/Users/alice/Desktop/IdeaProjects/OOP sandbox/src/OOPbox/Scholarships";
            File dir = new File(myDirectoryPath);
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                Integer[] schIDs = new Integer[directoryListing.length+1];
                int i=0;
                boolean atLeastOne = false;
                // TODO: deal with .DS_Store permanently
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
                    int month = scholarshipFile.nextInt() - 1;
//                scholarshipFile.next();
                    int day = scholarshipFile.nextInt();
                    deadline.set(year, month, day);
                    if (Calendar.getInstance().before(deadline)) // Today is before deadline
                    {
                        // Get maximum number of awardees
                        int awardeeLimit = scholarshipFile.nextInt();
                        scholarshipFile.nextLine(); // read \n

                        // Get number of current recipients
                        String rFileName = "/Users/alice/Desktop/IdeaProjects/OOP sandbox/src/OOPbox/Recipients/recipients" + scholarshipID + ".txt";

                        Scanner temp = new Scanner(new File(rFileName));
                        int numberOfLines = 0;
                        while (temp.hasNextLine()) {
                            temp.nextLine(); // read line
                            numberOfLines++;
                        }
                        int numberOfRecipients = numberOfLines;
                        temp.close();

                        // See if recipient limit reached
                        if (numberOfRecipients < awardeeLimit) // Scholarship can still be awarded
                        {
//                            // Check if student has previously been awarded the scholarship
//                            boolean newApplicant = true;
//                            Scanner temp2 = new Scanner(new File(rFileName));
//                            while (temp2.hasNextLine() && newApplicant) {
//                                String recipient = temp2.nextLine();
//                                if (String.valueOf(studentID).equals(recipient))
//                                    newApplicant = false;
//                            }
//                            temp2.close();
//
//                            if (newApplicant) // If student is new applicant
//                            {

                                // Check criteria
                                String major = scholarshipFile.nextLine();
                                int hours = scholarshipFile.nextInt();
                                scholarshipFile.nextLine(); // read \n
                                double gpa = scholarshipFile.nextDouble();
                                scholarshipFile.nextLine(); // read \n
                                if (student_criteria.getMajor().equals(major) && student_criteria.getHours() >= hours && student_criteria.getGpa() >= gpa) {
                                    if (!atLeastOne) {
                                        atLeastOne = true;
                                        System.out.println("You are eligible for the following scholarship(s): ");
                                    }

                                    // Get award amount
                                    double award = scholarshipFile.nextDouble();

                                    // Print scholarship details in format
                                    // Title:        X Scholarship    ID#: 1
                                    // Award amount: $500
                                    System.out.println("Title:        " + name + "\tID#: " + scholarshipID + "\nAward amount: $" + award);

                                    // Add scholarship ID to schIDs array
                                    schIDs[i] = scholarshipID;
                                    i++;
                                } else // Student is not eligible
                                {
                                    break;
                                }
                           //} //else // Student is already recipient of the scholarship
//                            {
//                                break;
//                            }
                        } else // Scholarship full
                        {
                            break;
                        }
                    } else // Today is after deadline
                    {
                        break;
                    }

                    // Read \n at end of file
                    scholarshipFile.nextLine();

                    scholarshipFile.close();

                    // Indicate end of eligible scholarship IDs
                    schIDs[i] = -1;

                }

                if (!atLeastOne) // Student is not eligible for any scholarships
                {
                    System.out.println("There are no scholarship matches.");
                } else // Student found to be eligible for at least 1 scholarship, which was/were printed
                {
                    Scanner stdin = new Scanner(System.in);
                    System.out.println("Please choose a scholarship by entering its ID number.");
                    int response = stdin.nextInt(); // TODO: validate this input
                    do {
                        System.out.println("Please enter the ID number of a scholarship from the provided list.");
                        response = stdin.nextInt();
                    } while(!Arrays.asList(schIDs).contains(response));

                    String recipientsFileName = "/Users/alice/Desktop/IdeaProjects/OOP sandbox/src/OOPbox/Recipients/recipients" + response + ".txt";
                    try {
                        FileWriter recipientsFile = new FileWriter(new File(recipientsFileName), true);
                        recipientsFile.write(studentID);
                        recipientsFile.write("\n");
                        recipientsFile.close();
                        System.out.println("You have been awarded the scholarship.");

                    } catch (IOException e) {
                        System.out.println("An error occurred while accessing recipients list.");
                    }

                    String studentFileName = "/Users/alice/ScholarshipSystem/OOP sandbox/src/OOPbox/Students/student" + studentID + ".txt";
                    try{
                        // TODO: rewrite file with isRecipient flag set to 1
                        FileWriter studentFile = new FileWriter(new File(studentFileName), true);
                        studentFile.write(response); // Write the ID of the chosen scholarship.
                        studentFile.close();
                    }
                    catch(IOException e)
                    {
                        System.out.println("An error occurred while accessing student file.");
                    }

                }
            } else {
                System.out.println("Error: not a directory.");
            }
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

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public boolean isScholarshipRecipient() {
        return isScholarshipRecipient;
    }

    public void setScholarshipRecipient(boolean isScholarshipRecipient) {
        this.isScholarshipRecipient = isScholarshipRecipient;
    }

}

