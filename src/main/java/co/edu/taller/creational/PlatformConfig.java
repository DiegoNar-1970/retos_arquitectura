package co.edu.taller.creational;

import java.util.Map;

/**
 * Singleton thread-safe mediante el idiom "initialization-on-demand holder":
 * la JVM garantiza que la clase Holder se inicializa una sola vez y de forma
 * segura entre hilos, sin necesidad de sincronización explícita.
 */
public class PlatformConfig {
    private final Map<String, String> values = Map.of("environment", "production");

    private PlatformConfig() {
    }

    private static final class Holder {
        private static final PlatformConfig INSTANCE = new PlatformConfig();
    }

    public static PlatformConfig instance() {
        return Holder.INSTANCE;
    }

    /**
     * @return el valor configurado, o {@code null} si la clave no existe
     * @throws IllegalArgumentException si la clave es nula o está en blanco
     */
    public String get(String key) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("La clave de configuración es obligatoria");
        }
        return values.get(key);
    }
}
