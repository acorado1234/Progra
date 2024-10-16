import java.util.*;
import java.util.stream.Collectors;

public class MatchingSystem {
  public  List<Student> students;
  public  List<Tutor> tutors;

  public MatchingSystem() {
    this.students = new ArrayList<>();
    this.tutors = new ArrayList<>();
  }

  public void addStudent(Student student) {
    students.add(student);
  }

  public void addTutor(Tutor tutor) {
    tutors.add(tutor);
  }

  public List<Tutor> findMatchesForStudent(Student student) {
    return tutors.stream()
            .filter(tutor -> hasCommonInterests(student, tutor))
            .filter(this::isAvailable)
            .sorted(Comparator.comparingDouble(tutor -> calculateMatchScore(student, tutor)))
            .collect(Collectors.toList());
  }

  public List<Student> findMatchesForTutor(Tutor tutor) {
    return students.stream()
            .filter(student -> hasCommonInterests(student, tutor))
            .sorted(Comparator.comparingDouble(student -> calculateMatchScore(student, tutor)))
            .collect(Collectors.toList());
  }

  private boolean hasCommonInterests(Student student, Tutor tutor) {
    Set<String> studentInterests = new HashSet<>(student.getInterests());
    Set<String> tutorSpecializations = new HashSet<>(tutor.getSpecializations());
    return !Collections.disjoint(studentInterests, tutorSpecializations);
  }

  private boolean isAvailable(Tutor tutor) {
    return !tutor.getAvailability().getAvailableTimes().isEmpty();
  }

  private double calculateMatchScore(Student student, Tutor tutor) {
    Set<String> studentInterests = new HashSet<>(student.getInterests());
    Set<String> tutorSpecializations = new HashSet<>(tutor.getSpecializations());
    Set<String> intersection = new HashSet<>(studentInterests);
    intersection.retainAll(tutorSpecializations);
    Set<String> union = new HashSet<>(studentInterests);
    union.addAll(tutorSpecializations);

    double commonInterestsScore = (double) intersection.size() / union.size();

    double ratingScore = tutor.getRating() / 5.0; 

    return 0.7 * commonInterestsScore + 0.3 * ratingScore;
  }

  public void notifyMatch(User user1, User user2) {
    System.out.println("Conexión encontrada entre " + user1.getName() + " y " + user2.getName());
  }
}
