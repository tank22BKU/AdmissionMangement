package Management;

import Object.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Object.AdmissionGroup;

import static java.lang.Thread.sleep;

public class AspirationProcessing {

    private static final Scanner sc = new Scanner(System.in);

    /*
     * @ This method will print guide to the terminal
     * @ User follows this guide to type the options program needs
     */
    public static ArrayList<Aspiration> gainCRUDRequest(ArrayList<Aspiration> listAspiration, ArrayList<Candidate> listCandidate) {
        System.out.println("\n----------------------------------------------------------------");
        System.out.println("---Gain Request for Adding, Modifying and Deleting Aspiration---");
        System.out.println("----------------------------------------------------------------\n");

        System.out.println("\n----------------------------------------------------------------");
        System.out.println("---         Type 1 for Adding new Aspiration                   ---");
        System.out.println("---         Type 2 for Modifying Aspiration                    ---");
        System.out.println("---         Type 3 for Deleting Aspiration                     ---");
        System.out.println("----------------------------------------------------------------\n");

        //Scanner 
        try{
            int choice = sc.nextInt();
            if (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("!!!! You have entered an invalid choice. !!!!");
            }

            switch (choice) {
                case 1:
                    return addAspiration(listAspiration);
                case 2:
                    return modifyAspiration(listAspiration);
                case 3:
                    return deleteAspiration(listAspiration, listCandidate);
            }
        } catch (InputMismatchException e) {
            System.out.println("!!!! Please enter a valid option !!!!" + e.getMessage());
        }
        return listAspiration;
    }


    /*
     * @ This method will add a new Aspiration into Aspiration List
     * @ User follows this guide to type the options program needs, especially AdmissionGroup
     */

    public static ArrayList<Aspiration> addAspiration(ArrayList<Aspiration> listAspiration){
        System.out.println("\n----------------------------------------------------------------");
        try{
            if(listAspiration == null){
                System.out.println("!!!! LIST ASPIRATION IS NULL !!!!");
                listAspiration = new ArrayList<Aspiration>();
            }

            //int candidateID;
            int aspirationID;
            int fieldID;
            String schoolID;
            String fieldName;
            String admissionGroup;
            AdmissionGroup aGroup;
            double floorScore;

            if(listAspiration.isEmpty()){
                aspirationID = 1;
            }else{
                aspirationID = listAspiration.getLast().getAspirationID() + 1;
            }

            //Scanner 
            //System.out.println("\n~~~Please enter Candidate ID (number) ~~~");
            //candidateID = sc.nextInt();
            System.out.println("\n~~~Please enter field ID (number) ~~~");
            if(sc.hasNextInt()) fieldID = sc.nextInt();
            else {
                System.out.println("!!!! You have typed an invalid field ID !!!!\n" + "Next to another process...");
                return listAspiration;
            }

            System.out.println("~~~Please enter school ID (String) ~~~");
            schoolID = sc.next();
            System.out.println("~~~Please enter field Name (String)~~~");
            fieldName = sc.next();
            System.out.println("~~~Please enter admission group (String; a00->a03, b00->b03)~~~");
            admissionGroup = sc.next();
            admissionGroup = admissionGroup.toLowerCase();
            System.out.println("~~~Please enter field Score (double)~~~");
            floorScore = sc.nextDouble();

            aGroup = switch (admissionGroup) {
                case "a00" -> AdmissionGroup.A00;
                case "a01" -> AdmissionGroup.A01;
                case "a02" -> AdmissionGroup.A02;
                case "a03" -> AdmissionGroup.A03;
                case "b00" -> AdmissionGroup.B00;
                case "b01" -> AdmissionGroup.B01;
                case "b02" -> AdmissionGroup.B02;
                case "b03" -> AdmissionGroup.B03;
                default -> AdmissionGroup.DEFAULT;
            };

            Aspiration newAspiration = new Aspiration(/*candidateID,*/aspirationID,fieldID,schoolID,fieldName,aGroup,floorScore);
            listAspiration.add(newAspiration);

            return listAspiration;

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch(InputMismatchException e){
            System.out.println("The input not match the requirement !! " + e.getMessage());
        }

        System.out.println("----------------------------------------------------------------\n");
        return null;
    }



    /*
     * @ Modify group will include 7 methods below
     * @ These methods will modify one Aspiration based on its ID
     * @ Then user notices the guide and types the field that user wants to modify
     * @ User follows this guide to type the options program needs
     */

    public static ArrayList<Aspiration> modifyAspiration(ArrayList<Aspiration> listAspiration){
        System.out.println("\n----------------------------------------------------------------");
        try{
            if(listAspiration == null){
                listAspiration = new ArrayList<Aspiration>();
            }

            if(listAspiration.isEmpty()) return listAspiration;

            //System.out.println("\n~~~Please enter Candidate ID needed to modify data (number) ~~~");
            //Scanner 
            //int candidateID = sc.nextInt();
            System.out.println("\n~~~Please enter Aspiration ID needed to modify data (number) ~~~");
            int aspirationID = sc.nextInt();

            System.out.println("\n~~~ Step1: Please enter which attributes needed to modify data (string)~~~");
            System.out.println("~~~ 1. fieldid (int)                                                    ~~~");
            System.out.println("~~~ 2. schoolid (String)                                                ~~~");
            System.out.println("~~~ 3. fieldname (String)                                               ~~~");
            System.out.println("~~~ 4. admissiongroup (String; a00->a03, b00->b03)                      ~~~");
            System.out.println("~~~ 5. floorscore (double)                                              ~~~");
            System.out.println("~~~ 6. aspirationid (int)                                              ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            int key = sc.nextInt();

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Step2: Please enter the new value fit to its type                   ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return switch (key) {
                case 1 -> modifyFieldID(listAspiration, aspirationID);
                case 2 -> modifySchoolID(listAspiration, aspirationID);
                case 3 -> modifyFieldName(listAspiration, aspirationID);
                case 4 -> modifyAdmissionGroup(listAspiration, aspirationID);
                case 5 -> modifyFloorScore(listAspiration, aspirationID);
                case 6 -> modifyAspirationID(listAspiration, aspirationID);
                default -> listAspiration;
            };

        } catch (InputMismatchException e){
            System.out.println("The input ModiAspi not match the requirement !! " + e.getMessage());
        }
        return null;
    }

    private static ArrayList<Aspiration> modifyFloorScore(ArrayList<Aspiration> listAspiration, /*int candidateID,*/ int aspirationID) {
        try{
            double floorScore = sc.nextDouble();

            listAspiration.stream().filter(asp -> asp.getAspirationID() == aspirationID)
                        .findFirst().ifPresent(asp -> asp.setFloorScore(floorScore));

            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input in ModiFloorScore not match the requirement !! " + e.getMessage());
            sc.nextLine();
        }
        return null;
    }

    private static ArrayList<Aspiration> modifyAdmissionGroup(ArrayList<Aspiration> listAspiration, /*int candidateID,*/ int aspirationID) {
        try{
            String admissionGroup = sc.next();
            AdmissionGroup newAdmissionGroup;
            newAdmissionGroup = switch (admissionGroup) {
                case "a00" -> AdmissionGroup.A00;
                case "a01" -> AdmissionGroup.A01;
                case "a02" -> AdmissionGroup.A02;
                case "a03" -> AdmissionGroup.A03;
                case "b00" -> AdmissionGroup.B00;
                case "b01" -> AdmissionGroup.B01;
                case "b02" -> AdmissionGroup.B02;
                case "b03" -> AdmissionGroup.B03;
                default -> AdmissionGroup.DEFAULT;
            };

            listAspiration.stream().filter(asp -> asp.getAspirationID() == aspirationID)
                    .findFirst().ifPresent(asp -> asp.setAdmissionGroup(newAdmissionGroup));

            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input in ModiAdGR not match the requirement !! " + e.getMessage());
        }  

        return listAspiration;
    }

    private static ArrayList<Aspiration> modifyFieldName(ArrayList<Aspiration> listAspiration, /*int candidateID,*/ int aspirationID) {
        try{
            String fieldName = sc.next();

            listAspiration.stream().filter(asp -> asp.getAspirationID() == aspirationID)
                    .findFirst().ifPresent(asp -> asp.setFieldName(fieldName));

            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input inModifieldName not match the requirement !! " + e.getMessage());
        } 

        return listAspiration;
    }

    private static ArrayList<Aspiration> modifySchoolID(ArrayList<Aspiration> listAspiration, /*int candidateID,*/ int aspirationID) {
        try{
            String schoolID = sc.next();

            listAspiration.stream().filter(asp -> asp.getAspirationID() == aspirationID)
                    .findFirst().ifPresent(asp -> asp.setSchoolID(schoolID));

            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input in ModiSchoID not match the requirement !! " + e.getMessage());
        } 

        return listAspiration;
    }

    private static ArrayList<Aspiration> modifyFieldID(ArrayList<Aspiration> listAspiration, /*int candidateID,*/ int aspirationID) {
        try{
            int fieldName = sc.nextInt();

            listAspiration.stream().filter(asp -> asp.getAspirationID() == aspirationID)
                    .findFirst().ifPresent(asp -> asp.setFieldID(fieldName));

            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input in ModiFieldID not match the requirement !! " + e.getMessage());
        } 

        return listAspiration;
    }

    public static ArrayList<Aspiration> modifyAspirationID(ArrayList<Aspiration> listAspiration, /*int candidateID,*/ int aspirationID) {
        try{
            int newAspirationID = sc.nextInt();

            listAspiration.stream().filter(asp -> asp.getAspirationID() == aspirationID)
                    .findFirst().ifPresent(asp -> asp.setAspirationID(newAspirationID));


            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input in modiAspiID not match the requirement !! " + e.getMessage());
        } 

        return listAspiration;
    }



    /*
     * @ This method will remove a Aspiration out of Aspiration List based on its ID
     * @ User follows this guide to type the options program needs, especially AdmissionGroup
     */

    public static ArrayList<Aspiration> deleteAspiration(ArrayList<Aspiration> listAspiration, ArrayList<Candidate> listCandidate) {
        try{
            int aspirationID = sc.nextInt();

            //if there is any Candidate have the aspirationID in their aspirationID then return
            //else remove aspiration from list aspiration
            boolean isUsed = listCandidate.stream().anyMatch(candidate ->
                    candidate.getListAspiration().stream().anyMatch(candidateAspiration ->
                            candidateAspiration.getAspirationID() == aspirationID
                    )
            );
            if(!isUsed) listAspiration.removeIf(asp -> asp.getAspirationID() == aspirationID);

            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input in deleteaspi not match the requirement !! " + e.getMessage());
            sc.nextLine();
        }
        return listAspiration;
    }


    /*
     * @ This method will print the Aspiration List based on user's requirements.
     * @ User can apply any changes if needed
     */

    public static void printListAspiration(ArrayList<Aspiration> listAspiration){
        try{

            listAspiration.forEach(asp -> asp.printInf());

        }catch(NullPointerException e){
            System.out.println("!!!! There is no Aspiration found !!!!" + e.getMessage());
        }

    }

    /*
     * @ Search group will include 7 methods below
     * @ These methods will search for one Aspiration based on its field's value
     * @ Then user notices the guide and types the field and its value that user wants to find
     * @ User follows this guide to type the option program needs
     */

    public static void searchForAspiration(ArrayList<Aspiration> listAspiration) {
        try{
            System.out.println("\n~~~ Step1: Please enter which attributes needed to find (string)~~~");
            System.out.println("~~~ 1. fieldid (int)                                                    ~~~");
            System.out.println("~~~ 2. schoolid (String)                                                ~~~");
            System.out.println("~~~ 3. fieldname (String)                                               ~~~");
            System.out.println("~~~ 4. admissiongroup (String; a00->a03, b00->b03)                      ~~~");
            System.out.println("~~~ 5. floorscore (double)                                              ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            int key = sc.nextInt();

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~ Step2: Please enter the value you want to search for                ~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            switch (key) {
                case 1:
                    findFieldID(listAspiration);
                case 2:
                    findSchoolID(listAspiration);
                case 3:
                    findFieldName(listAspiration);
                case 4:
                    findAdmissionGroup(listAspiration);
                case 5:
                    findFloorScore(listAspiration);
                default:
                    break;
            }

        } catch (InputMismatchException e){
            System.out.println("The input in searchAspi not match the requirement !! " + e.getMessage());
        }
    }

    private static void findFloorScore(ArrayList<Aspiration> listAspiration) {
        try{
            double floorScore = sc.nextDouble();

            listAspiration.stream().filter(asp -> asp.getFloorScore() >= floorScore)
                    .forEach(asp -> asp.printInf());

        } catch (InputMismatchException e){
            System.out.println("The input in findfloorscore not match the requirement !! " + e.getMessage());
        }
    }

    private static void findAdmissionGroup(ArrayList<Aspiration> listAspiration) {
        try{
            String admissionGroup = sc.next();
            AdmissionGroup aGroup;
            aGroup= switch (admissionGroup){
                case "a00" -> AdmissionGroup.A00;
                case "a01" -> AdmissionGroup.A01;
                case "a02" -> AdmissionGroup.A02;
                case "a03" -> AdmissionGroup.A03;
                case "b00" -> AdmissionGroup.B00;
                case "b01" -> AdmissionGroup.B01;
                case "b02" -> AdmissionGroup.B02;
                case "b03" -> AdmissionGroup.B03;
                default -> AdmissionGroup.DEFAULT;
            };

            AdmissionGroup finalAGroup = aGroup;
            listAspiration.stream().filter(asp -> asp.getAdmissionGroup().equals(finalAGroup))
                    .forEach(asp -> asp.printInf());

        } catch (InputMismatchException | NullPointerException e){
            System.out.println("The input in findadmissionGr not match the requirement !! " + e.getMessage());
        }
    }

    private static void findFieldName(ArrayList<Aspiration> listAspiration) {
        try{
            String fieldName = sc.next();
            listAspiration.stream().filter(asp -> asp.getFieldName().equals(fieldName))
                    .forEach(asp -> asp.printInf());
        } catch (InputMismatchException | NullPointerException e){
            System.out.println("The input in findfieldname not match the requirement !! " + e.getMessage());
        } 

    }

    private static void findSchoolID(ArrayList<Aspiration> listAspiration) {
        try{
            String schoolID = sc.next();
            listAspiration.stream().filter(asp -> asp.getSchoolID().equals(schoolID))
                    .forEach(asp -> asp.printInf());
        } catch (InputMismatchException | NullPointerException e){
            System.out.println("The input in findschoolid not match the requirement !! " + e.getMessage());
            sc.nextLine();
        }
    }

    private static void findFieldID(ArrayList<Aspiration> listAspiration) {
        try{
            int fieldID = sc.nextInt();
            boolean has = listAspiration.stream().anyMatch(asp -> asp.getFieldID() == fieldID);


            if(!has) System.out.println("The fieldID is not found in the list");
            else listAspiration.stream().filter(asp -> asp.getFieldID() == fieldID)
                    .forEach(asp -> asp.printInf());
        } catch (InputMismatchException | NullPointerException e){
            System.out.println("The input in findfieldid not match the requirement !! " + e.getMessage());
        }
    }

    /*
     * This main method is created to test some methods above
     * User can make some changes to test method above if needed
     */


    public static void main(String[] args) {
        //Aspiration a = new Aspiration(1,101, "BKA" ,"Computer Science", AdmissionGroup.A00, 20.5);
        try {
            ArrayList<Aspiration> listAspiration = null; //new ArrayList<Aspiration>();
            //listAspiration.add(a);
            printListAspiration(listAspiration);
            listAspiration = addAspiration(listAspiration);
            listAspiration = addAspiration(listAspiration);
            listAspiration = modifyAspiration(listAspiration);
            printListAspiration(listAspiration);

            sleep(3000);
            searchForAspiration(listAspiration);
        } catch (InputMismatchException | NullPointerException e) {
            System.out.println("The input in main not match the requirement !! " + e.getMessage());
        } catch (RuntimeException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
