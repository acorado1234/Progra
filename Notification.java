import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
    protected  String id;
    protected User recipient;
    protected String message;
    protected LocalDateTime timestamp;
    protected boolean isRead;

    public Notification(User recipient, String message) {
        this.id = UUID.randomUUID().toString();
        this.recipient = recipient;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.isRead = false;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    // Getters
    public String getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isRead() {
        return isRead;
    }
}
