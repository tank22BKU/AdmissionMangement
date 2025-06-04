package Management;

import Object.Aspiration;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Object.AdmissionGroup;

public class AspirationProcessing {

    private static final Scanner sc = new Scanner(System.in);

    public static ArrayList<Aspiration> gainCRUDRequest(ArrayList<Aspiration> listAspiration) {
        System.out.println("\n----------------------------------------------------------------");
        System.out.println("---Gain Request for Adding, Modifying and Deleting Aspiration---");
        System.out.println("----------------------------------------------------------------\n");

        System.out.println("\n----------------------------------------------------------------");
        System.out.println("---         Type 1 for Adding new Aspiration                   ---");
        System.out.println("---         Type 2 for Modifying Aspiration                    ---");
        System.out.println("---         Type 3 for Deleting Aspiration                     ---");
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
                    case 1: return addAspiration(listAspiration);
                    case 2: return modifyAspiration(listAspiration);
                    case 3: return deleteAspiration(listAspiration);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("!!!! Please enter a valid option !!!!" + e.getMessage());
        }
        return listAspiration;
    }

    public static ArrayList<Aspiration> addAspiration(ArrayList<Aspiration> listAspiration){
        System.out.println("\n----------------------------------------------------------------");
        try{
            if(listAspiration == null){
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

            //Scanner sc = new Scanner(System.in);
            //System.out.println("\n~~~Please enter Candidate ID (number) ~~~");
            //candidateID = sc.nextInt();
            System.out.println("\n~~~Please enter field ID (number) ~~~");
            fieldID = sc.nextInt();
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

    public static ArrayList<Aspiration> modifyAspiration(ArrayList<Aspiration> listAspiration){
        System.out.println("\n----------------------------------------------------------------");
        try{
            if(listAspiration == null){
                listAspiration = new ArrayList<Aspiration>();
            }

            if(listAspiration.isEmpty()) return listAspiration;

            //System.out.println("\n~~~Please enter Candidate ID needed to modify data (number) ~~~");
            //Scanner sc = new Scanner(System.in);
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

            while(sc.hasNextInt()){
                int key = sc.nextInt();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("~~~ Step2: Please enter the new value fit to its type                   ~~~");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                switch (key) {
                    case 1 : return modifyFieldID(listAspiration, /*candidateID,*/ aspirationID);
                    case 2 : return modifySchoolID(listAspiration, /*candidateID,*/ aspirationID);
                    case 3 : return modifyFieldName(listAspiration, /*candidateID,*/ aspirationID);
                    case 4 : return modifyAdmissionGroup(listAspiration, /*candidateID,*/ aspirationID);
                    case 5 : return modifyFloorScore(listAspiration, /*candidateID,*/ aspirationID);
                    case 6 : return modifyAspirationID(listAspiration,  aspirationID);
                    default : break;
                }
            }

            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input not match the requirement !! " + e.getMessage());
        }
        return null;
    }

    private static ArrayList<Aspiration> modifyFloorScore(ArrayList<Aspiration> listAspiration, /*int candidateID,*/ int aspirationID) {
        try{
            double floorScore = sc.nextDouble();
            for(Aspiration asp : listAspiration){
                if(asp.getAspirationID() == aspirationID){
                    asp.setFloorScore(floorScore);
                    break;
                }
            }
            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input not match the requirement !! " + e.getMessage());
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

            for(Aspiration asp : listAspiration){
                if(asp.getAspirationID() == aspirationID){
                    asp.setAdmissionGroup(newAdmissionGroup);
                    break;
                }
            }
            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input not match the requirement !! " + e.getMessage());
        }
        return listAspiration;
    }

    private static ArrayList<Aspiration> modifyFieldName(ArrayList<Aspiration> listAspiration, /*int candidateID,*/ int aspirationID) {
        try{
            String fieldName = sc.next();
            for(Aspiration asp : listAspiration){
                if(asp.getAspirationID() == aspirationID){
                    asp.setFieldName(fieldName);
                    break;
                }
            }
            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input not match the requirement !! " + e.getMessage());
        }
        return listAspiration;
    }

    private static ArrayList<Aspiration> modifySchoolID(ArrayList<Aspiration> listAspiration, /*int candidateID,*/ int aspirationID) {
        try{
            String schoolID = sc.next();
            for(Aspiration asp : listAspiration){
                if(asp.getAspirationID() == aspirationID){
                    asp.setSchoolID(schoolID);
                    break;
                }
            }
            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input not match the requirement !! " + e.getMessage());
        }
        return listAspiration;
    }

    private static ArrayList<Aspiration> modifyFieldID(ArrayList<Aspiration> listAspiration, /*int candidateID,*/ int aspirationID) {
        try{
            int fieldName = sc.nextInt();
            for(Aspiration asp : listAspiration){
                if(asp.getAspirationID() == aspirationID){
                    asp.setFieldID(fieldName);
                    break;
                }
            }
            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input not match the requirement !! " + e.getMessage());
        }
        return listAspiration;
    }

    public static ArrayList<Aspiration> modifyAspirationID(ArrayList<Aspiration> listAspiration, /*int candidateID,*/ int aspirationID) {
        try{
            int newAspirationID = sc.nextInt();
            for(Aspiration asp : listAspiration){
                if(asp.getAspirationID() == aspirationID){
                    asp.setAspirationID(newAspirationID);
                    break;
                }
            }
            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input not match the requirement !! " + e.getMessage());
        }
        return listAspiration;
    }

    public static ArrayList<Aspiration> deleteAspiration(ArrayList<Aspiration> listAspiration/*, int candidateID, int aspirationID*/) {
        try{
            int aspirationID = sc.nextInt();
            for(Aspiration asp : listAspiration){
                if(asp.getAspirationID() == aspirationID){
                    listAspiration.remove(asp);
                    break;
                }
            }
            return listAspiration;
        } catch (InputMismatchException e){
            System.out.println("The input not match the requirement !! " + e.getMessage());
        }
        return listAspiration;
    }

    public static void printListAspiration(ArrayList<Aspiration> listAspiration){
        try{
            for (Aspiration aspiration : listAspiration) {
                aspiration.printInf();
            }
        }catch(NullPointerException e){
            System.out.println("!!!! There is no Aspiration found !!!!" + e.getMessage());
        }

    }

    public static void searchForAspiration(ArrayList<Aspiration> listAspiration) {

    }

    /*
    public static void main(String[] args) {
        Aspiration a = new Aspiration(1,101, "BKA" ,"Computer Science", AdmissionGroup.A00, 20.5);
        ArrayList<Aspiration> listAspiration = null; //new ArrayList<Aspiration>();
        //listAspiration.add(a);
        printListAspiration(listAspiration);
        listAspiration = addAspiration(listAspiration);
        printListAspiration(listAspiration);
        listAspiration = modifyAspiration(listAspiration);
        printListAspiration(listAspiration);
    }
    */
}
