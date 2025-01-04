package SpringSequrity.SpringSequre;

public class StudentEntity {
    private int rollNumber;
    private String name;
    private String tech;

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public StudentEntity(int rollNumber, String name, String tech) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.tech = tech;
    }
}
