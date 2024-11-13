import java.util.List;
import java.util.ArrayList;

public class Student extends User {
    private String major;
    private List<String> interests; 


    public Student(String id, String name, String email, String password, String major, List<String> interests) {
        super(id, name, email, password);
        this.major = major;
        this.interests = interests; 
    }

  
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Major: " + major);
        System.out.println("Interests: " + interests);
    }
}
