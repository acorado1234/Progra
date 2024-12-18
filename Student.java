import java.util.List;
import java.util.ArrayList;

public class Student extends User {
    private String major;
    private List<String> interests; 
    private ArrayList<TestResult> results; 


    public Student(String id, String name, String email, String password, String major, List<String> interests) {
        super(id, name, email, password);
        this.major = major;
        this.interests = interests;
        this.results = new ArrayList<>();
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

    public void addResult(TestResult result){
        results.add(result);
    }

    public void showPastResulst(){
        for(TestResult test : results){
            test.mostrarResultado();
        }
    }
}
