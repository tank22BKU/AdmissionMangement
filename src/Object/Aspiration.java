package Object;

import Object.AdmissionGroup;

public class Aspiration {
    /*
     * The value of aspirationID, fieldID are greater than 0
     * If the value is not valid, set it to 1
     */
    //private int candidateID;
    private int aspirationID;
    private int fieldID;

    private String schoolID;
    private String fieldName;
    private AdmissionGroup admissionGroup;

    /*
     * The value of floorScore is greater than 0
     * If the value is not valid, set it to 0
     */
    private double floorScore;

    public Aspiration(/*int cdID,*/int id, int fieldID, String schoolID, String fieldName, AdmissionGroup admissionGroup, double floorScore) {
        //if(cdID <= 0) cdID = 1;
        if(id <= 0) id = 1;
        if(fieldID <= 0) fieldID = 1;
        if(floorScore < 0) floorScore = 0;
        if(admissionGroup == null || AdmissionGroup.isValid(admissionGroup)) this.admissionGroup = AdmissionGroup.DEFAULT;

        //this.candidateID = cdID;
        this.aspirationID = id;
        this.fieldID = fieldID;
        this.schoolID = schoolID;
        this.fieldName = fieldName;
        this.admissionGroup = admissionGroup;
        this.floorScore = floorScore;
    }

    /*
    public int getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(int candidateID) {
        this.candidateID = candidateID;
    }
    */

    public int getAspirationID() {
        return aspirationID;
    }

    public void setAspirationID(int aspirationID) {
        if(aspirationID <= 0) aspirationID = 1;
        this.aspirationID = aspirationID;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        if(fieldID <= 0) fieldID = 1;
        this.fieldID = fieldID;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public AdmissionGroup getAdmissionGroup() {
        return admissionGroup;
    }

    public void setAdmissionGroup(AdmissionGroup admissionGroup) {
        if(admissionGroup != AdmissionGroup.DEFAULT) this.admissionGroup = admissionGroup;
    }

    public double getFloorScore() {
        return floorScore;
    }

    public void setFloorScore(double floorScore) {
        if(floorScore < 0) floorScore = 0;
        this.floorScore = floorScore;
    }

    public void printInf(){
        System.out.println("----------------------------------------------------");
        System.out.println("----       ~~ Aspiration Information ~~         ----");
        System.out.println("----------------------------------------------------");
        System.out.println("---------Aspiration ID: " + aspirationID);
        System.out.println("---------Field ID: " + fieldID);
        System.out.println("---------School ID: " + schoolID);
        System.out.println("---------Field Name: " + fieldName);
        System.out.println("---------Admission Group: " + admissionGroup);
        System.out.println("---------Floor Score: " + floorScore);
        System.out.println("----------------------------------------------------");
    }

}
