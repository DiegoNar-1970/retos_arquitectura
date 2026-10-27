package co.edu.taller.creational;

public class NotificationFactory {
    public interface Notifier {
        String send(String recipient, String message);
    }

    public enum Channel {EMAIL, SMS}

    /**
     * Factory Method: el cliente solo conoce la interfaz {@link Notifier};
     * la implementación concreta se decide aquí según el canal.
     */
    public Notifier create(Channel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("El canal de notificación es obligatorio");
        }
        return switch (channel) {
            case EMAIL -> new EmailNotifier();
            case SMS -> new SmsNotifier();
        };
    }

    private static String format(String prefix, String recipient, String message) {
        if (recipient == null || recipient.isBlank()) {
            throw new IllegalArgumentException("El destinatario es obligatorio");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("El mensaje es obligatorio");
        }
        return prefix + ":" + recipient + ":" + message;
    }

    private static final class EmailNotifier implements Notifier {
        @Override
        public String send(String recipient, String message) {
            return format("EMAIL", recipient, message);
        }
    }

    private static final class SmsNotifier implements Notifier {
        @Override
        public String send(String recipient, String message) {
            return format("SMS", recipient, message);
        }
    }
}
