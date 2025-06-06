package Management;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Object.Proctor;

public class ProctorProcessing {

    private static final Scanner sc = new Scanner(System.in);

    /*
     * @ This method will print guide to the terminal
     * @ User follows this guide to type the options program needs
     */

    public static ArrayList<Proctor> gainCRUDRequest(ArrayList<Proctor> listProctor) {
        System.out.println("\n----------------------------------------------------------------");
        System.out.println("---Gain Request for Adding, Modifying and Deleting proctors-  --");
        System.out.println("----------------------------------------------------------------\n");

        System.out.println("\n----------------------------------------------------------------");
        System.out.println("---         Type 1 for Adding new Proctor                    ---");
        System.out.println("---         Type 2 for Modifying Proctor                     ---");
        System.out.println("---         Type 3 for Deleting Proctor                      ---");
        System.out.println("----------------------------------------------------------------\n");

        //Scanner sc = new Scanner(System.in);
        try{
            int choice = sc.nextInt(); sc.nextLine();
            if (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("!!!! You have entered an invalid choice. !!!!");
            }

            return switch (choice) {
                case 1 -> addNewProctor(listProctor);
                case 2 -> modifyproctor(listProctor);
                case 3 -> deleteProctor(listProctor);
                default -> listProctor;
            };

        } catch(InputMismatchException e){
            System.out.println("!!!! Please enter a valid option !!!!" + e.getMessage());
        }
        return listProctor;
    }

    /*
     * @ This method will add new Proctor into the Candidate's AspirationList
     * @ input : candidateID
     */

    private static ArrayList<Proctor> addNewProctor(ArrayList<Proctor> listProctor) {
        try{
            System.out.println("\n----------------------------------------------------------------------");
            System.out.println("----------------------                          ----------------------");
            System.out.println("----------------------   Creating new Proctor   ----------------------");
            System.out.println("----------------------                          ----------------------");
            System.out.println("----------------------------------------------------------------------");
            if(listProctor == null) listProctor = new ArrayList<Proctor>();

            String id;
            String fullName;
            String gender;
            int yearOfBirth;
            String workPlace;

            System.out.println("\n~~~Please enter the ID of the Proctor! (String)~~~");
            id = sc.next(); sc.nextLine();
            //if match any element in the List return
            if (listProctor.stream().anyMatch(obj -> obj.getId().equals(id)) ){
                System.out.println("!!! This ID : " + id + " already exists in the list !!!");
                return listProctor;
            }

            System.out.println("\n~~~Please enter the full name of the Proctor! (String)~~~");
            fullName = sc.nextLine();

            System.out.println("\n~~~Please enter the gender of the Proctor! (male/female)~~~");
            gender = sc.next(); sc.nextLine();
            boolean Gender = gender.equals("female");

            System.out.println("\n~~~Please enter the yearOfBirth of the Proctor! (int)~~~");
            yearOfBirth = sc.nextInt(); sc.nextLine();

            System.out.println("\n~~~Please enter the WorkPlace of the Proctor! (String)~~~");
            workPlace = sc.nextLine();

            Proctor res = new Proctor(id, fullName, Gender, yearOfBirth, workPlace);
            listProctor.add(res);

            return listProctor;
        } catch(InputMismatchException e){
            System.out.println("!!!! Input in addNewCandidate is invalid !!!!" + e.getMessage());
        }
        return listProctor;
    }

    private static ArrayList<Proctor> modifyproctor(ArrayList<Proctor> listProctor) {

        return listProctor;
    }

    /*
     * @ This method will remove a Proctor out of Proctor List based on its ID
     * @ User follows this guide to type the options program needs
     */

    private static ArrayList<Proctor> deleteProctor(ArrayList<Proctor> listProctor) {
        try{
            System.out.println("\n----------------------------------------------------------------------");
            System.out.println("----------------------                          ----------------------");
            System.out.println("----------------------     Delete Proctor       ----------------------");
            System.out.println("----------------------                          ----------------------");
            System.out.println("----------------------------------------------------------------------");

            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Please enter the id of the Proctor need to remove        (String) ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String id = sc.next();

            if(listProctor == null ||listProctor.isEmpty()) {
                System.out.println("There is no Proctor to remove");
                return listProctor;
            }

            listProctor.stream().filter(proctor -> proctor.getId().equals(id))
                    .findFirst().ifPresent(listProctor::remove);

            return listProctor;

        } catch (InputMismatchException e){
            System.out.println("Input in deleteProctor is mismatch" + e.getMessage());
        }
        return listProctor;
    }

    /*
     * @ This method will print the Proctor List based on user's requirements.
     * @ User can apply any changes if needed
     */

    public static void printListProctor(ArrayList<Proctor> listProctor) {
        if(listProctor == null || listProctor.isEmpty()){
            System.out.println("!!!! There are no proctors in the list !!!!");
            return;
        }

        listProctor.forEach(obj -> obj.printInf());
    }

    /*
     * Finding Group include 5 methods, which finds appropriate Proctor based on its values
     *  @
     */

    public static void searchForProctor(ArrayList<Proctor> listProctor) {
        try{
            if(listProctor == null || listProctor.isEmpty()) {
                System.out.println("\n!!!! There are no Proctors in the list !!!!" );
                return;
            }

            System.out.println("\n~~~ Step1: Please enter which attributes you want to find         (int)~~~");
            System.out.println("~~~ 1. Name (String)                                                    ~~~");
            System.out.println("~~~ 2. Gender (String : male/female)                                    ~~~");
            System.out.println("~~~ 3. Proctor ID  (String)                                             ~~~");
            System.out.println("~~~ 4. WorkPlace (String )                                              ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            int key = sc.nextInt(); sc.nextLine();
            switch(key){
                case 1: searchForProctorWithName(listProctor); break;
                case 2: searchForProctorWithGender(listProctor); break;
                case 3: searchForProctorWithID(listProctor); break;
                case 4: searchForProctorWithWorkPlace(listProctor); break;
                default: return;
            }

        } catch(InputMismatchException e){
            System.out.println("Input in searchForProctor is invalid " + e.getMessage());
        }
    }

    private static void searchForProctorWithName(ArrayList<Proctor> listProctor) {
        try{
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~Please enter the Name of the Proctor need to find             (String)~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String name = sc.nextLine();

            boolean found = false;

            found = listProctor.stream().anyMatch(proctor -> proctor.getId().equals(name));

            if(!found){
                System.out.println("!!!! The ID of the Proctor is not found !!!!" );
                return;
            }

            listProctor.stream().filter(proctor -> proctor.getName().equals(name))
                    .findFirst().ifPresent(proctor -> proctor.printInf());

        } catch(InputMismatchException e){
            System.out.println("Input in searchForProctorWithID is invalid " + e.getMessage());
        }
    }

    private static void searchForProctorWithGender(ArrayList<Proctor> listProctor) {
        try{
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~Please enter the Gender of the Proctor need to find     (male/female)~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String gender = sc.next(); sc.nextLine();
            boolean Gender = gender.equals("female");

            boolean found = false;

            found = listProctor.stream().anyMatch(proctor -> proctor.getGender() == Gender);

            if(!found){
                System.out.println("!!!! The ID of the proctor is not found !!!!" );
                return;
            }

            listProctor.forEach(proctor -> {
                if(proctor.getGender() == Gender){
                    proctor.printInf();
                }
            });

        } catch(InputMismatchException e){
            System.out.println("Input in searchForProctorWithID is invalid " + e.getMessage());
        }
    }

    private static void searchForProctorWithID(ArrayList<Proctor> listProctor) {
        try{
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~Please enter the ID of the Proctor need to find               (String)~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String id = sc.next(); sc.nextLine();

            boolean found = false;

            found = listProctor.stream().anyMatch(proctor -> proctor.getId().equals(id));

            if(!found){
                System.out.println("!!!! The ID of the candidate is not found !!!!" );
                return;
            }

            listProctor.stream().filter(proctor -> proctor.getId().equals(id))
                    .findFirst().ifPresent(proctor -> proctor.printInf());

        } catch(InputMismatchException e){
            System.out.println("Input in searchForProctorWithID is invalid " + e.getMessage());
        }
    }

    private static void searchForProctorWithWorkPlace(ArrayList<Proctor> listProctor) {
        try{
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~Please enter the WorkPlace of the Proctor need to find        (String)~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String workPlace = sc.nextLine();

            boolean found = false;

            found = listProctor.stream().anyMatch(proctor -> proctor.getWorkPlace().equals(workPlace));

            if(!found){
                System.out.println("!!!! The ID of the candidate is not found !!!!" );
                return;
            }

            listProctor.stream().filter(proctor -> proctor.getWorkPlace().equals(workPlace))
                    .findFirst().ifPresent(proctor -> proctor.printInf());

        } catch(InputMismatchException e){
            System.out.println("Input in searchForProctorWithWorkPlace is invalid " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ArrayList<Proctor> listProctor = null;
        listProctor = addNewProctor(listProctor);
        listProctor = addNewProctor(listProctor);
        listProctor = addNewProctor(listProctor);
        listProctor = addNewProctor(listProctor);
        searchForProctorWithID(listProctor);
        printListProctor(listProctor);
    }
}
