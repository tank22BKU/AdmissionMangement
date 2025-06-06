package Management;

import Object.*;

import java.io.*;
import java.util.*;

public class IOFileHandling {

    /*
     * This method will check if is there any Candidate/ Proctor ID is exist in the List
     * if exist -> true
     * else -> false
     */

    public static boolean checkIfExists(ArrayList<Proctor> listProctor, ArrayList<Candidate> listCandidate, String ID){
        try{
            if(listProctor == null && listCandidate == null){
                return false;
            }

            if(listProctor != null){
                if(listProctor.isEmpty()){
                    return false;
                }

                return listProctor.stream().anyMatch(proctor -> proctor.getId().equals(ID));
            }

            if(listCandidate.isEmpty()){
                return false;
            }

            return listCandidate.stream().anyMatch(candidate -> candidate.getId().equals(ID));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /*
     * This method will use File Stream to input Protor Object Data into the System
     * With below format
     * [<id>]
     * [<name>]
     * [<gender> <yearOfBirth>]
     * [<workPlace>]
     * [emptyline]
     * [<anotherProcterID>]
     * [...]
     * [...]
     * Warning : Do not leave any filed blank/ null !!!!!
     */

    public static ArrayList<Proctor> streamProctorInput(ArrayList<Proctor> listProctor) {
        try{
            if(listProctor == null){
                listProctor = new ArrayList<Proctor>();
            }
            File input = new File("src/FileInput/inputDataForProctor");
            Scanner sc = new Scanner(input);
            while (sc.hasNextLine()) {
                String id = sc.next(); sc.nextLine();
                if(checkIfExists(listProctor, null, id)){
                    System.out.println("The id : " + id + " already exists");
                    sc.nextLine();
                    sc.nextLine();
                    sc.nextLine();
                    if (sc.hasNextLine()) sc.nextLine();
                    continue;
                }

                String name = sc.nextLine();
                String gender = sc.next();
                int yearOfBirth = Integer.parseInt(sc.next()); sc.nextLine();
                String workPlace = sc.nextLine();

                boolean Gender = gender.equals("female");
                Proctor res = new Proctor(id, name, Gender, yearOfBirth, workPlace);
                listProctor.add(res);

                if (sc.hasNextLine()) sc.nextLine();
            }

            sc.close();
            return listProctor;
        } catch (FileNotFoundException e) {
            System.out.println("File ProctorInput Not Found " + e.getMessage());
        }
        return listProctor;
    }

    /*
     * This method will use File Stream to input Candidate Object Data into the System
     * With below format
     * [<id>]
     * [<name>]
     * [<gender> <yearOfBirth>]
     * [<examinationScore> <priorityScore>]
     * [<homeTown>]
     * [emptyline]
     * [<anotherCandidateID>]
     * [...]
     * [...]
     * Warning : Do not leave any filed blank/ null !!!!!
     */

    public static ArrayList<Candidate> streamCandidateInput(ArrayList<Candidate> listCandidate) {
        try{
            if(listCandidate == null){
                listCandidate = new ArrayList<Candidate>();
            }
            File input = new File("src/FileInput/inputDataForCandidate");
            Scanner sc = new Scanner(input);
            while (sc.hasNextLine()) {
                String id = sc.next(); sc.nextLine();
                if(checkIfExists(null, listCandidate, id)){
                    System.out.println("The id : " + id + " already exists");
                    sc.nextLine();
                    sc.nextLine();
                    sc.nextLine();
                    sc.nextLine();
                    if (sc.hasNextLine()) sc.nextLine();
                    continue;
                }

                String name = sc.nextLine();
                String gender = sc.next();
                int yearOfBirth = Integer.parseInt(sc.next()); sc.nextLine();
                double examinationScore = sc.nextDouble();
                double priorScore = sc.nextDouble(); sc.nextLine();
                String homeTown = sc.nextLine();

                boolean Gender = gender.equals("female");
                Candidate res = new Candidate(id, name, Gender, yearOfBirth, examinationScore, priorScore, null, homeTown);
                listCandidate.add(res);

                if (sc.hasNextLine()) sc.nextLine();
            }

            sc.close();
            return listCandidate;
        } catch (FileNotFoundException e) {
            System.out.println("File ProctorInput Not Found " + e.getMessage());
        }
        return listCandidate;
    }

    /*
     * WriteCandidatePassExamFollowAspirationID method will filter Candiates who have enough Score
     * (ExaminationScore > AspirationScore) to pass
     * Then this method will sorting the CandidateList based on AspirationID
     */

    public static class SortByAspirationID implements Comparator<Candidate> {
        public int compare(Candidate o1, Candidate o2) {
            int o1ID = o1.getListAspiration().getFirst().getAspirationID();
            int o2ID = o2.getListAspiration().getFirst().getAspirationID();

            if(o1ID > o2ID) return 1;
            else if(o1ID < o2ID) return -1;
            else{
                double examScore1 = o1.getExaminationScore();
                double examScore2 = o2.getExaminationScore();
                if(examScore1 > examScore2) return 1;
                else if(examScore1 < examScore2) return -1;
                return 0;
            }
        }
    }

    public static boolean isPass(Candidate candidate){
        try{
            ArrayList<Aspiration> listAspiration = candidate.getListAspiration();
            if(listAspiration == null || listAspiration.isEmpty()){
                return false;
            }

            boolean isNotPass = listAspiration.stream().noneMatch(aspiration -> candidate.getExaminationScore() + candidate.getPriorityScore() >= aspiration.getFloorScore());
            if(isNotPass){
                return false;
            }

            // if the Candidate has the available aspiration, set it to first
            listAspiration.stream().filter(aspiration -> candidate.getExaminationScore() + candidate.getPriorityScore() >= aspiration.getFloorScore())
                    .findFirst().ifPresent(aspiration ->listAspiration.set(0, aspiration));

            candidate.setListAspiration(listAspiration);

        } catch(NullPointerException e){
            System.out.println("pointer in is Pass is null " + e.getMessage());
        }
        return true;
    }

    public static String filterCandidatePassExamFollowCondition(ArrayList<Candidate> listCandidate, String filter) {
        try{
            ArrayList<Candidate> cloneCandidateList = new ArrayList<Candidate>();
            listCandidate.forEach(candidate -> {
                if(isPass(candidate)){
                    cloneCandidateList.add(candidate);
                }
            });

            if(filter.equals("aspiration"))  Collections.sort(cloneCandidateList, new SortByAspirationID());
            else Collections.sort(cloneCandidateList, new SortBySchoolAndFieldID());

            StringBuilder res = new StringBuilder();


            for(int i = 0; i < cloneCandidateList.size(); i++){
                String inf = "Aspiration ID : " + cloneCandidateList.get(i).getListAspiration().getFirst().getAspirationID() + "; FloorScore : " + cloneCandidateList.get(i).getListAspiration().getFirst().getFloorScore()
                        + "; Candidate ID : " + cloneCandidateList.get(i).getId() + "; Name : " + cloneCandidateList.get(i).getName() + "; Gender : " + cloneCandidateList.get(i).getGender()
                        + "; ExaminationScore : " + cloneCandidateList.get(i).getExaminationScore() + "; PriorityScore : " + cloneCandidateList.get(i).getPriorityScore();

                if(!filter.equals("aspiration")){
                    String addition = "SchoolID : " + cloneCandidateList.get(i).getListAspiration().getFirst().getSchoolID()
                            + "; FieldID : " + cloneCandidateList.get(i).getListAspiration().getFirst().getFieldID() + "; ";
                    inf = addition + inf;
                }

                if(i == 0) res = new StringBuilder(inf + "\n");
                else{
                    if(cloneCandidateList.get(i).getListAspiration().getFirst().getAspirationID() == cloneCandidateList.get(i-1).getListAspiration().getFirst().getAspirationID()){
                        res.append(inf).append("\n");
                    }else{
                        res = new StringBuilder(res + "\n" + inf + "\n");
                    }
                }
            }

            return res.toString();
        }  catch(NullPointerException e){
            System.out.println("pointer in filterCandidatePassExamFollowAspirationID is null " + e.getMessage());
        }
        return "";
    }


    public static void WriteCandidatePassExamToFileFollowAspirationID(ArrayList<Candidate> listCandidate){
        try{
            FileWriter output = new FileWriter("src/FileOutput/outputCandidateData");

            String data = filterCandidatePassExamFollowCondition(listCandidate, "aspiration");
            output.write(data);

            output.close();
        } catch (IOException e){
            System.out.println("IO Candidate Output Error" + e.getMessage());
        }
    }

    /*
     * This class will make the nearly same thing with WriteCandidatePassExamToFileFollowAspirationID
     * but the only difference is WriteCandidatePassExamToFileFollowAspirationID is written to the File
     * when display into the screen
     */

    public static void displayListCandidatePassExamFollowAspirationID(ArrayList<Candidate> listCandidate) {
        try{
            System.out.println(filterCandidatePassExamFollowCondition(listCandidate, "aspiration"));
        } catch (NullPointerException e){
            System.out.println("pointer in displayListCandidatePassExamFollowAspirationID is null " + e.getMessage());
        }
    }


    /*
     * displayListCandidatePassExamFollowSchoolAndFieldID will do the nearly thing with displayListCandidatePassExamFollowAspirationID
     * when displayListCandidatePassExamFollowAspirationID filter result by AspirationID
     * displayListCandidatePassExamFollowSchoolAndFieldID filter result by SchoolID and FieldID
     * @
     */

    public static class SortBySchoolAndFieldID implements Comparator<Candidate> {
        public int compare(Candidate o1, Candidate o2) {
            String o1SchoolID = o1.getListAspiration().getFirst().getSchoolID();
            String o2SchoolID = o2.getListAspiration().getFirst().getSchoolID();

            int o1FieldID = o1.getListAspiration().getFirst().getFieldID();
            int o2FieldID = o2.getListAspiration().getFirst().getFieldID();

            if(o1SchoolID.compareTo(o2SchoolID) == 0){
                return o1FieldID - o2FieldID;
            }else{
                return o1SchoolID.compareTo(o2SchoolID);
            }
        }
    }

    public static void displayListCandidatePassExamFollowSchoolAndFieldID(ArrayList<Candidate> listCandidate) {
        try{
            System.out.println(filterCandidatePassExamFollowCondition(listCandidate, "schoolAndFieldID"));
        } catch (NullPointerException e){
            System.out.println("pointer in displayListCandidatePassExamFollowAspirationID is null " + e.getMessage());
        }
    }


    /*
     * This main method is created to test some methods above
     * User can make some changes to test method above if needed
     */

    public static void main(String[] args) {
//        ArrayList<Proctor> listProctor = streamProctorInput(null);
//        listProctor.forEach(proctor -> {
//            proctor.printInf();
//        });

        ArrayList<Candidate> listCandidate = streamCandidateInput(null);

        Aspiration a1 = new Aspiration(1,1,"BKA", "AI", AdmissionGroup.A00,8);
        Aspiration a2 = new Aspiration(1,1,"BKA", "AI", AdmissionGroup.A00,6);
        Aspiration a3 = new Aspiration(1,1,"BKA", "AI", AdmissionGroup.A00,4);
        Aspiration a4 = new Aspiration(2,1,"UEH", "AI", AdmissionGroup.A00,8);
        Aspiration a5 = new Aspiration(2,1,"UEH", "AI", AdmissionGroup.A00,6);
        Aspiration a6 = new Aspiration(2,1,"UEH", "AI", AdmissionGroup.A00,4);
        Aspiration a7 = new Aspiration(3,1,"HUIT", "AI", AdmissionGroup.A00,8);
        Aspiration a8 = new Aspiration(3,1,"HUIT", "AI", AdmissionGroup.A00,6);
        Aspiration a9 = new Aspiration(3,1,"HUIT", "AI", AdmissionGroup.A00,4);
        Aspiration a10 = new Aspiration(4,1,"USSH", "AI", AdmissionGroup.A00,8);
        Aspiration a11 = new Aspiration(4,1,"USSH", "AI", AdmissionGroup.A00,6);
        Aspiration a12 = new Aspiration(4,1,"USSH", "AI", AdmissionGroup.A00,4);

        listCandidate.get(0).setListAspiration(new ArrayList<Aspiration>());
        listCandidate.get(1).setListAspiration(new ArrayList<Aspiration>());
        listCandidate.get(2).setListAspiration(new ArrayList<Aspiration>());
        listCandidate.get(3).setListAspiration(new ArrayList<Aspiration>());


        listCandidate.get(0).getListAspiration().add(a1);
        listCandidate.get(0).getListAspiration().add(a2);
        listCandidate.get(0).getListAspiration().add(a3);
        listCandidate.get(1).getListAspiration().add(a4);
        listCandidate.get(1).getListAspiration().add(a5);
        listCandidate.get(1).getListAspiration().add(a6);
        listCandidate.get(2).getListAspiration().add(a7);
        listCandidate.get(2).getListAspiration().add(a8);
        listCandidate.get(2).getListAspiration().add(a9);
        listCandidate.get(3).getListAspiration().add(a10);
        listCandidate.get(3).getListAspiration().add(a11);
        listCandidate.get(3).getListAspiration().add(a12);


        displayListCandidatePassExamFollowSchoolAndFieldID(listCandidate);
    }
}
