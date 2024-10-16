import java.util.*;

public class NotificationSystem {
    public  Map<User, List<Notification>> userNotifications;

    public NotificationSystem() {
        this.userNotifications = new HashMap<>();
    }

    public void sendNotification(User recipient, String message) {
        Notification notification = new Notification(recipient, message);
        userNotifications.computeIfAbsent(recipient, k -> new ArrayList<>()).add(notification);
    }

    public List<Notification> getNotificationsForUser(User user) {
        return userNotifications.getOrDefault(user, new ArrayList<>());
    }

    public void markNotificationAsRead(User user, String notificationId) {
        List<Notification> notifications = userNotifications.get(user);
        if (notifications != null) {
            for (Notification notification : notifications) {
                if (notification.getId().equals(notificationId)) {
                    notification.markAsRead();
                    break;
                }
            }
        }
    }
}
