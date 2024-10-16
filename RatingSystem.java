import java.time.LocalDateTime;
import java.util.*;

public class RatingSystem {
    
    private Map<Tutor, List<Rating>> tutorRatings;

  
    public RatingSystem() {
        this.tutorRatings = new HashMap<>();
    }


    public void addRating(Tutor tutor, Student student, int score, String comment) {
        Rating rating = new Rating(student, score, comment);
        tutorRatings.computeIfAbsent(tutor, k -> new ArrayList<>()).add(rating);
        updateTutorRating(tutor);
    }

   
    private void updateTutorRating(Tutor tutor) {
        List<Rating> ratings = tutorRatings.get(tutor);
        if (ratings != null && !ratings.isEmpty()) {
            double averageRating = ratings.stream()
                .mapToInt(Rating::getScore)
                .average()
                .orElse(0.0);
            tutor.setRating((float) averageRating);
        }
    }

    
    public List<Rating> getRatingsForTutor(Tutor tutor) {
        return tutorRatings.getOrDefault(tutor, new ArrayList<>());
    }

   
    public static class Rating {
        private Student student;
        private int score;
        private String comment;
        private LocalDateTime timestamp;

      
        public Rating(Student student, int score, String comment) {
            this.student = student;
            this.score = score;
            this.comment = comment;
            this.timestamp = LocalDateTime.now();
        }

      
        public Student getStudent() { return student; }

       
        public int getScore() { return score; }

      
        public String getComment() { return comment; }

        public LocalDateTime getTimestamp() { return timestamp; }

    }
}
