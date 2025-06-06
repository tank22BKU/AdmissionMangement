package Object;

public class Proctor extends Person {
    private String workPlace;

    public Proctor(String id, String name, boolean gender, int yearOfBirth, String workPlace) {
        super(id, name, gender, yearOfBirth);
        this.workPlace = workPlace;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public void printInf(){
        System.out.println("\n***************************************************");
        System.out.println("****       ~~ Proctor Information ~~            ***");
        System.out.println("***************************************************\n");
        System.out.println("*********Proctor ID: " + this.getId());
        System.out.println("*********Full name : " + this.getName());
        System.out.println("*********Gender : " + (this.getGender() ? "FEMALE" : "MALE"));
        System.out.println("*********Year of birth : " + this.getYearOfBirth());
        System.out.println("*********Home town : " + this.workPlace);
        System.out.println("******************************************************\n");
    }
}
