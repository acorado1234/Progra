import java.util.List;

public class Tutor extends User {
    private List<String> specializations;//Lista especializaciones 
    protected Schedule availability;
    protected String subjectExpertise; 
    protected float rating;

    
    public Tutor(String id, String name, String email, String password, String subjectExpertise, List<String> specializations) {
        super(id, name, email, password);
        this.availability = new Schedule();
        this.subjectExpertise = subjectExpertise;
        this.specializations = specializations;  
        this.rating = 0;
    }


    public String getSubjectExpertise() {
        return subjectExpertise;
    }

    public void setSubjectExpertise(String subjectExpertise) {
        this.subjectExpertise = subjectExpertise;
    }

    
    public List<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }

    
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

   
    public Schedule getAvailability() {
        return availability;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Subject Expertise: " + subjectExpertise);
        System.out.println("Specializations: " + String.join(", ", specializations));
        System.out.println("Rating: " + rating);
    }
}
