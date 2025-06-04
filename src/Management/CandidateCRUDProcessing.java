package Management;

import Object.Candidate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CandidateCRUDProcessing {

    public static Scanner sc = new Scanner(System.in);

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
            while(sc.hasNextInt()){
                int choice = sc.nextInt();
                if(choice != 1 && choice != 2 && choice != 3){
                    System.out.println("!!!! Please enter a valid option !!!!");
                    continue;
                }

                switch (choice) {
                    case 1: return addNewCandidate(listCandidate);
                    case 2: return modifyCandidate(listCandidate);
                    case 3: return deleteCandidate(listCandidate);
                }
            }
        } catch(InputMismatchException e){
            System.out.println("!!!! Please enter a valid option !!!!" + e.getMessage());
        }
        return listCandidate;
    }

    public static ArrayList<Candidate> addNewCandidate(ArrayList<Candidate> listCandidate){
        return listCandidate;
    }

    public static ArrayList<Candidate> modifyCandidate(ArrayList<Candidate> listCandidate){
        return listCandidate;
    }

    public static ArrayList<Candidate> deleteCandidate(ArrayList<Candidate> listCandidate){
        return listCandidate;
    }
}


