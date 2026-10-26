package co.edu.taller.creational;

public class NotificationFactory {
    public interface Notifier {
        String send(String recipient, String message);
    }

    public enum Channel {EMAIL, SMS}

    public Notifier create(Channel channel) {
        // TODO: Return an implementation appropriate for the requested channel.
        throw new UnsupportedOperationException("Implement Factory Method");
    }
}