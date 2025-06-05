package Object;

import Object.Person;
import Object.Aspiration;
import java.util.ArrayList;

public class Candidate extends Person {
    /*
     * The value of examinationScore, priorityScore are greater than 0
     * If the value is not valid, set it to 0
     */
    private double examinationScore;
    private double priorityScore;
    private ArrayList<Aspiration> listAspiration;
    private String homeTown;

    public Candidate(int id, String name, boolean gender, int yearOfBirth, double examinationScore, double priorityScore, ArrayList<Aspiration> listAspi,String homeTown) {
        super(id, name, gender, yearOfBirth);
        this.examinationScore = examinationScore;
        this.priorityScore = priorityScore;
        this.listAspiration = listAspi;
        this.homeTown = homeTown;
    }

    public double getExaminationScore() {
        return examinationScore;
    }

    public double getPriorityScore() {
        return priorityScore;
    }

    public ArrayList<Aspiration> getListAspiration() {
        return listAspiration;
    }

    public void setPriorityScore(double priorityScore) {
        if(this.priorityScore < 0) priorityScore = 0;
        this.priorityScore = priorityScore;
    }

    public void setListAspiration(ArrayList<Aspiration> listAspiration) {
        this.listAspiration = listAspiration;
    }

    public void setExaminationScore(double examinationScore) {
        if(this.examinationScore < 0) examinationScore = 0;
        this.examinationScore = examinationScore;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public void printInf(){
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("++++       ~~ Candidate Information ~~          +++");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("+++++++++Candidate ID: " + this.getId());
        System.out.println("+++++++++Full name : " + this.getName());
        System.out.println("+++++++++Gender : " + (this.getGender() ? "FEMALE" : "MALE"));
        System.out.println("+++++++++Year of birth : " + this.getYearOfBirth());
        System.out.println("+++++++++Home town : " + this.homeTown);
        System.out.println("+++++++++Examination Score : " + this.examinationScore);
        System.out.println("+++++++++Priority Score : " + this.priorityScore);

        if(this.listAspiration != null) this.listAspiration.forEach(a -> a.printInf());
        else System.out.println("---------This candidate has not signed any aspirations");

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }
}
