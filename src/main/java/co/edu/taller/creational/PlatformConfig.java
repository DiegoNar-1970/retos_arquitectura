package co.edu.taller.creational;

public class PlatformConfig {
    // TODO: implement thread-safe Singleton and expose instance() and get(key).
    public static PlatformConfig instance() {
        throw new UnsupportedOperationException("Implement Singleton");
    }

    public String get(String key) {
        throw new UnsupportedOperationException("Implement configuration lookup");
    }
}