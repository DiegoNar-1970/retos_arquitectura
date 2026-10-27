package co.edu.taller.behavioral;

import java.util.EnumMap;
import java.util.Map;

public class ShippingRateStrategy {
    public interface Rate {
        long calculate(long base);
    }

    public enum Mode {STANDARD, EXPRESS, NIGHT}

    /** Cada modo se asocia a su estrategia; se pueden intercambiar en tiempo de ejecución. */
    private final Map<Mode, Rate> rates = new EnumMap<>(Mode.class);

    public ShippingRateStrategy() {
        rates.put(Mode.STANDARD, base -> base);          // sin recargo
        rates.put(Mode.EXPRESS, base -> base * 18 / 10); // x1.8
        rates.put(Mode.NIGHT, base -> base * 15 / 10);   // x1.5
    }

    /**
     * Strategy: delega el cálculo en la estrategia asociada al modo.
     *
     * @throws IllegalArgumentException si el modo es nulo o la base no es positiva
     */
    public long quote(Mode mode, long base) {
        if (mode == null) {
            throw new IllegalArgumentException("El modo de envío es obligatorio");
        }
        if (base <= 0) {
            throw new IllegalArgumentException("La tarifa base debe ser mayor que cero");
        }
        return rates.get(mode).calculate(base);
    }
}
