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

            String id;
            String fullName;
            String gender;
            int yearOfBirth;
            String homeTown;
            double examinationScore;
            double priorityScore;
            ArrayList<Aspiration> listAspiration = null;

            System.out.println("\n~~~Please enter the ID of the candidate! (String)~~~");
            id = sc.next(); sc.nextLine();
            //if match any element in the List return
            if (listCandidate.stream().anyMatch(obj -> obj.getId().equals(id)) ){
                System.out.println("!!! This ID : " + id + " already exists in the list !!!");
                return listCandidate;
            }

            System.out.println("\n~~~Please enter the full name of the candidate! (String)~~~");
            fullName = sc.nextLine();

            System.out.println("\n~~~Please enter the gender of the candidate! (male/female)~~~");
            gender = sc.next(); sc.nextLine();
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
        try{
            if(listCandidate == null || listCandidate.isEmpty()) {
                System.out.println("!!! There are no candidate in List !!!");
                return listCandidate;
            }

            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Please enter the id of the candidate need to Modify data (String) ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String candidateID = sc.next(); sc.nextLine();

            System.out.println("\n~~~ Step1: Please enter which attributes needed to modify data (string)~~~");
            System.out.println("~~~ 1. Name (String)                                                    ~~~");
            System.out.println("~~~ 2. Gender (String : male/female)                                    ~~~");
            System.out.println("~~~ 3. YearOfBirth (int)                                                ~~~");
            System.out.println("~~~ 4. ExaminationScore (double)                                        ~~~");
            System.out.println("~~~ 5. PriorityScore (double)                                           ~~~");
            System.out.println("~~~ 6. HomeTown (String)                                                ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            int key = sc.nextInt(); sc.nextLine();
            return switch (key) {
                case 1 -> modifyCandidateName(listCandidate, candidateID);
                case 2 -> modifyCandidateGender(listCandidate, candidateID);
                case 3 -> modifyCandidateYearOfBirth(listCandidate, candidateID);
                case 4 -> modifyCandidateExaminationScore(listCandidate, candidateID);
                case 5 -> modifyCandidatePriorityScore(listCandidate, candidateID);
                case 6 -> modifyCandidateHomeTown(listCandidate, candidateID);
                default -> listCandidate;
            };
        } catch (InputMismatchException e){
            System.out.println("!!!! Input in modifyCandidate is invalid !!!!" + e.getMessage());
        }

        return listCandidate;
    }

    private static ArrayList<Candidate> modifyCandidateName(ArrayList<Candidate> listCandidate, String candidateID) {
        try{
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Please enter the NEW NAME of the candidate               (String) ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String newName = sc.nextLine();

            listCandidate.stream().filter(candidate -> candidate.getId().equals(candidateID))
                    .forEach(candidate -> candidate.setName(newName));

            return listCandidate;
        } catch (InputMismatchException e){
            System.out.println("!!!! Input in modifyCandidateName is invalid !!!!" + e.getMessage());
        }
        return listCandidate;
    }

    private static ArrayList<Candidate> modifyCandidateGender(ArrayList<Candidate> listCandidate, String candidateID) {
        try{
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Please enter the NEW Gender of the candidate             (String) ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String newGender = sc.next(); sc.nextLine();
            boolean genderExists = newGender.equals("female");

            listCandidate.stream().filter(candidate -> candidate.getId().equals(candidateID))
                    .forEach(candidate -> candidate.setGender(genderExists));

            return listCandidate;
        } catch (InputMismatchException e){
            System.out.println("!!!! Input in modifyCandidateName is invalid !!!!" + e.getMessage());
        }
        return listCandidate;
    }

    private static ArrayList<Candidate> modifyCandidateYearOfBirth(ArrayList<Candidate> listCandidate, String candidateID) {
        try{
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Please enter the NEW Year of Birth of the candidate      (String) ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            int newYOB = sc.nextInt(); sc.nextLine();

            listCandidate.stream().filter(candidate -> candidate.getId().equals(candidateID))
                    .forEach(candidate -> candidate.setYearOfBirth(newYOB));

            return listCandidate;
        } catch (InputMismatchException e){
            System.out.println("!!!! Input in modifyCandidateName is invalid !!!!" + e.getMessage());
        }
        return listCandidate;
    }

    private static ArrayList<Candidate> modifyCandidateExaminationScore(ArrayList<Candidate> listCandidate, String candidateID) {
        try{
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Please enter the NEW ExaminationScore of the candidate   (String) ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            double newExaminationScore = sc.nextInt(); sc.nextLine();

            listCandidate.stream().filter(candidate -> candidate.getId().equals(candidateID))
                    .forEach(candidate -> candidate.setExaminationScore(newExaminationScore));

            return listCandidate;
        } catch (InputMismatchException e){
            System.out.println("!!!! Input in modifyCandidateName is invalid !!!!" + e.getMessage());
        }
        return listCandidate;
    }

    private static ArrayList<Candidate> modifyCandidatePriorityScore(ArrayList<Candidate> listCandidate, String candidateID) {
        try{
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Please enter the NEW ExaminationScore of the candidate   (String) ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            double newPriorityScore = sc.nextInt(); sc.nextLine();

            listCandidate.stream().filter(candidate -> candidate.getId().equals(candidateID))
                    .forEach(candidate -> candidate.setPriorityScore(newPriorityScore));

            return listCandidate;
        } catch (InputMismatchException e){
            System.out.println("!!!! Input in modifyCandidateName is invalid !!!!" + e.getMessage());
        }
        return listCandidate;
    }

    private static ArrayList<Candidate> modifyCandidateHomeTown(ArrayList<Candidate> listCandidate, String candidateID) {
        try{
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Please enter the NEW NAME of the candidate               (String) ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String newHomeTown = sc.nextLine();

            listCandidate.stream().filter(candidate -> candidate.getId().equals(candidateID))
                    .forEach(candidate -> candidate.setHomeTown(newHomeTown));

            return listCandidate;
        } catch (InputMismatchException e){
            System.out.println("!!!! Input in modifyCandidateName is invalid !!!!" + e.getMessage());
        }
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

            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Please enter the id of the candidate need to remove      (String) ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String id = sc.next();

            if(listCandidate == null ||listCandidate.isEmpty()) {
                System.out.println("There is no candidate to remove");
                return listCandidate;
            }

            listCandidate.stream().filter(candidate -> candidate.getId().equals(id))
                    .findFirst().ifPresent(listCandidate::remove);

            return listCandidate;

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
            System.out.println("~~~Please enter the id of the candidate need to add Aspiration(String)~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String id = sc.next(); sc.nextLine();

            if(id.isEmpty()){
                System.out.println("!!!! The index is in valid !!!!" );
                return listCandidate;
            }

            listCandidate.stream().filter(candidate -> candidate.getId().equals(id))
                    .findFirst()
                    .ifPresent(candidate -> candidate.setListAspiration(AspirationProcessing.addAspiration(candidate.getListAspiration())));

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
                return listCandidate;
            }

            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~Please enter the id of the candidate need to remove Aspiration(String)~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String id = sc.next();

            ArrayList<Aspiration> listAspiration = null;
            for(Candidate candidate : listCandidate){
                if(candidate.getId().equals(id)){
                    listAspiration = candidate.getListAspiration();
                }
            }

            if(listAspiration == null || listAspiration.isEmpty()) {
                System.out.println("!!!! There is no Aspiration to delete !!!!" );
                return listCandidate;
            }
            if(id.isEmpty()){
                System.out.println("!!!! The index is in valid !!!!" );
                return listCandidate;
            }

            System.out.println("\nPlease enter the ID of the Aspiration you want to delete...");
            int aspirationID = sc.nextInt(); sc.nextLine();

            listAspiration.removeIf(asp -> asp.getAspirationID() == aspirationID);

            //Update the entire ListAspiration ID
            ArrayList<Aspiration> finalListAspiration = listAspiration;
            listAspiration.forEach(asp -> {
                asp.setAspirationID(finalListAspiration.indexOf(asp) + 1);
            });
            listCandidate.stream().filter(candidate -> candidate.getId().equals(id)).
                    forEach(candidate -> candidate.setListAspiration(finalListAspiration));

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
        listCandidate = modifyCandidate(listCandidate);
        printListCandiate(listCandidate);
    }
}





