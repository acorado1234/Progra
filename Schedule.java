import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<TimeSlot> availableTimes;

    public Schedule() {
        this.availableTimes = new ArrayList<>();
    }


    
    public void addTimeSlot(TimeSlot slot) {
        availableTimes.add(slot);
    }

  
    public void removeTimeSlot(TimeSlot slot) {
        availableTimes.remove(slot);
    }

   
    public List<TimeSlot> getAvailableTimes() {
        return this.availableTimes;
    }

}
