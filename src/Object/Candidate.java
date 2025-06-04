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
}
