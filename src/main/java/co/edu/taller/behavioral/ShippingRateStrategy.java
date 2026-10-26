package co.edu.taller.behavioral;

public class ShippingRateStrategy {
    public interface Rate {
        long calculate(long base);
    }

    public enum Mode {STANDARD, EXPRESS, NIGHT}

    public long quote(Mode mode, long base) { // TODO: delegate to selected strategy
        throw new UnsupportedOperationException("Implemente Strategy");
    }
}