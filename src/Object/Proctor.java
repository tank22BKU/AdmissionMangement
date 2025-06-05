package Object;

public class Proctor extends Person {
    private String workPlace;

    public Proctor(int id, String name, boolean gender, int yearOfBirth, String workPlace) {
        super(id, name, gender, yearOfBirth);
        this.workPlace = workPlace;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }
}
