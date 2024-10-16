// AcademicActivity.java
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AcademicActivity{
    private String id;
    private Tutor tutor;
    private List<Student> students;
    private String title;
    private String description;
    private int maxCapacity;
    private int currentEnrollment;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public AcademicActivity(Tutor tutor, String title, String description, int maxCapacity, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.id = UUID.randomUUID().toString();
        this.tutor = tutor;
        this.title = title;
        this.description = description;
        this.maxCapacity = maxCapacity;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.students = new ArrayList<>();
        this.currentEnrollment = 0;
    }

     public void addStudent(Student student) {
        if (currentEnrollment < maxCapacity && !students.contains(student)) {
            students.add(student);
            currentEnrollment++;
        }
    }
        public void removeStudent(Student student) {
        if (students.remove(student)) {
            currentEnrollment--;
        }
    }
    // Getters
    public String getId() { return id; }
    public Tutor getTutor() { return tutor; }
    public List<Student> getEnrolledStudents() { return new ArrayList<>(students); }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getMaxCapacity() { return maxCapacity; }
    public int getCurrentEnrollment() { return currentEnrollment; }
    public LocalDateTime getStartDateTime() { return startDateTime; }
    public LocalDateTime getEndDateTime() { return endDateTime; }
}
