package Management;

import Object.Candidate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Object.Aspiration;

public class CandidateCRUDProcessing {

    public static final Scanner sc = new Scanner(System.in);

    /*
     * @ This method will print guide to the terminal
     * @ User follows this guide to type the options program needs
     */

    public static ArrayList<Candidate> gainCRUDRequest(ArrayList<Candidate> listCandidate) {
        System.out.println("\n----------------------------------------------------------------");
        System.out.println("---Gain Request for Adding, Modifying and Deleting Aspiration---");
        System.out.println("----------------------------------------------------------------\n");

        System.out.println("\n----------------------------------------------------------------");
        System.out.println("---         Type 1 for Adding new Candidate                    ---");
        System.out.println("---         Type 2 for Modifying Candidate                     ---");
        System.out.println("---         Type 3 for Deleting Candidate                      ---");
        System.out.println("----------------------------------------------------------------\n");

        //Scanner sc = new Scanner(System.in);
        try{
            int choice = sc.nextInt();
            if (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("!!!! You have entered an invalid choice. !!!!");
            }

            return switch (choice) {
                case 1 -> addNewCandidate(listCandidate);
                case 2 -> modifyCandidate(listCandidate);
                case 3 -> deleteCandidate(listCandidate);
                default -> listCandidate;
            };

        } catch(InputMismatchException e){
            System.out.println("!!!! Please enter a valid option !!!!" + e.getMessage());
        }
        return listCandidate;
    }


    /*
     * @ This method will add a new Candidate into Aspiration List
     * @ User follows this guide to type the options program needs, especially AdmissionGroup
     */

    public static ArrayList<Candidate> addNewCandidate(ArrayList<Candidate> listCandidate){
        try{
            System.out.println("\n----------------------------------------------------------------------");
            System.out.println("----------------------                          ----------------------");
            System.out.println("----------------------Creating new Candidate ...----------------------");
            System.out.println("----------------------                          ----------------------");
            System.out.println("----------------------------------------------------------------------");
            if(listCandidate == null) listCandidate = new ArrayList<Candidate>();

            int id;
            String fullName;
            String gender;
            int yearOfBirth;
            String homeTown;
            double examinationScore;
            double priorityScore;
            ArrayList<Aspiration> listAspiration = null;

            if(listCandidate.isEmpty()) id = 1;
            else id = listCandidate.getLast().getId() + 1;

            System.out.println("\n~~~Please enter the full name of the candidate! (String)~~~");
            fullName = sc.nextLine();

            System.out.println("\n~~~Please enter the gender of the candidate! (male/female)~~~");
            gender = sc.next();
            boolean Gender = gender.equals("female");

            System.out.println("\n~~~Please enter the yearOfBirth of the candidate! (int)~~~");
            yearOfBirth = sc.nextInt(); sc.nextLine();

            System.out.println("\n~~~Please enter the home town of the candidate! (String)~~~");
            homeTown = sc.nextLine();

            System.out.println("\n~~~Please enter the examination score of the candidate! (double)~~~");
            examinationScore = sc.nextDouble(); sc.nextLine();

            System.out.println("\n~~~Please enter the priority score of the candidate! (double)~~~");
            priorityScore = sc.nextDouble(); sc.nextLine();

            Candidate res = new Candidate(id, fullName, Gender, yearOfBirth, examinationScore, priorityScore, listAspiration, homeTown);
            listCandidate.add(res);

            return listCandidate;
        } catch(InputMismatchException e){
            System.out.println("!!!! Input in addNewCandidate is invalid !!!!" + e.getMessage());
        }
        return listCandidate;
    }

    /*
     * @ Modify group will include 7 methods below
     * @ These methods will modify one Aspiration based on its ID
     * @ Then user notices the guide and types the field that user wants to modify
     * @ User follows this guide to type the options program needs
     */

    public static ArrayList<Candidate> modifyCandidate(ArrayList<Candidate> listCandidate){
        return listCandidate;
    }


    /*
     * @ This method will remove a Candidate out of Candidate List based on its ID
     * @ User follows this guide to type the options program needs
     */

    public static ArrayList<Candidate> deleteCandidate(ArrayList<Candidate> listCandidate){
        try{
            System.out.println("\n----------------------------------------------------------------------");
            System.out.println("----------------------                          ----------------------");
            System.out.println("----------------------     Delete Candidate     ----------------------");
            System.out.println("----------------------                          ----------------------");
            System.out.println("----------------------------------------------------------------------");

            System.out.println("\n~~~ Please enter the id of the candidate need to remove! (int)~~~");
            int id = sc.nextInt(); sc.nextLine();

            if(listCandidate == null ||listCandidate.isEmpty()) {
                System.out.println("There is no candidate to remove");
                return listCandidate;
            }

        } catch (InputMismatchException e){
            System.out.println("Input in deleteCandidate is mismatch" + e.getMessage());
        }
        return listCandidate;
    }

    /*
     * @ This method will print the Aspiration List based on user's requirements.
     * @ User can apply any changes if needed
     */

    public static void printListCandiate(ArrayList<Candidate> listCandidate){
        try{
            if(listCandidate == null || listCandidate.isEmpty()) {
                System.out.println("\nThere are no candidates in the list");
                return;
            }
            listCandidate.forEach(asp -> asp.printInf());

        }catch(NullPointerException e){
            System.out.println("!!!! There is no Aspiration found !!!!" + e.getMessage());
        }

    }


    /*
     * @ This method will add new Aspiration into the Candidate's AspirationList
     * @ input : candidateID
     */

    public static ArrayList<Candidate> addNewAspirationForCandidate(ArrayList<Candidate> listCandidate){
        try{
            if(listCandidate == null || listCandidate.isEmpty()) {
                System.out.println("\n!!!! There are no candidates in the list !!!!");
                return listCandidate;
            }

            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Please enter the id of the candidate need to add Aspiration (int) ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            int id = sc.nextInt(); sc.nextLine();

            System.out.println("SIze : " + listCandidate.size() );

            if(id > listCandidate.size() || id <= 0){
                System.out.println("!!!! The index is in valid !!!!" );
                return listCandidate;
            }
            ArrayList<Aspiration> listAspiration = listCandidate.get(id-1).getListAspiration();
            listCandidate.get(id-1).setListAspiration(AspirationProcessing.addAspiration(listAspiration));

            return listCandidate;
        } catch (NullPointerException e){
            System.out.println("This pointer in addNewAspirationForCandidate is null " + e.getMessage());
        } catch(InputMismatchException e){
            System.out.println("Input in addNewAspirationForCandidate is invalid " + e.getMessage());
        }

        return listCandidate;
    }

    /*
     * @ This method will remove one Aspiration from the Candidate's AspirationList
     * @ input : candidateID
     * @ input : aspirationID
     */

    public static ArrayList<Candidate> deleteAspirationForCandidate(ArrayList<Candidate> listCandidate){
        try{
            if(listCandidate == null || listCandidate.isEmpty()) {
                System.out.println("\n!!!! There are no candidates in the list !!!!");
            }

            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Please enter the id of the candidate need to remove Aspiration (int) ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            int id = sc.nextInt(); sc.nextLine();

            ArrayList<Aspiration> listAspiration = listCandidate.get(id-1).getListAspiration();
            if(listAspiration == null || listAspiration.isEmpty()) {
                System.out.println("!!!! There is no Aspiration to delete !!!!" );
                return listCandidate;
            }
            if(id > listCandidate.size() || id <= 0){
                System.out.println("!!!! The index is in valid !!!!" );
                return listCandidate;
            }

            System.out.println("\nPlease enter the ID of the Aspiration you want to delete...");
            int aspirationID = sc.nextInt(); sc.nextLine();

            listAspiration.removeIf(asp -> asp.getAspirationID() == aspirationID);

            //Update the entire ListAspiration ID
            listAspiration.forEach(asp -> {
                asp.setAspirationID(listAspiration.indexOf(asp) + 1);
            });
            listCandidate.get(id-1).setListAspiration(listAspiration);

            return listCandidate;
        } catch(InputMismatchException e){
            System.out.println("Input in deleteAspirationForCandidate is invalid " + e.getMessage());
        } catch (NullPointerException e){
            System.out.println("This pointer in deleteAspirationForCandidate is null " + e.getMessage());
        }

        return listCandidate;
    }

    /*
     * This main method is created to test some methods above
     * User can make some changes to test method above if needed
     */

    public static void main(String[] args) {
        ArrayList<Candidate> listCandidate = addNewCandidate(null);
        listCandidate = addNewAspirationForCandidate(listCandidate);
        listCandidate = addNewAspirationForCandidate(listCandidate);
        printListCandiate(listCandidate);
        listCandidate = deleteAspirationForCandidate(listCandidate);
        printListCandiate(listCandidate);
    }
}





