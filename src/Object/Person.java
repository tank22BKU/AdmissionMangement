package Object;

/*
 * Because Candidate and Supervisor have some same attributes, so i segregate them into a Person class
 * @id
 * @name
 * @gender
 * @yearOfBirth
 * @homeTown
 */

public class Person{
    /*
     * The value of id is unique and greater than 0
     * If the value is not valid, set it to 1
     */
    private int id;

    private String name;

    /*
     * true is female
     * false is female
     */
    private boolean gender = true;

    /*
     * The value of yearOfBirth is greater than 1900
     */
    private int yearOfBirth;

    public Person(int id, String name, boolean gender, int yearOfBirth){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id <= 0) id = 1;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

}
