package co.edu.taller.structural;

/**
 * Decorator: el costo base se enriquece con recargos (seguro, prioridad)
 * componiendo decoradores, sin crear una clase por cada combinación.
 */
public interface ShippingCost {
    long cost();

    /** Componente concreto: tarifa base de envío. */
    final class Standard implements ShippingCost {
        static final long BASE = 12_000;

        @Override
        public long cost() {
            return BASE;
        }
    }

    /** Decorador base: envuelve otro {@link ShippingCost}. */
    abstract class Decorator implements ShippingCost {
        protected final ShippingCost delegate;

        protected Decorator(ShippingCost delegate) {
            if (delegate == null) {
                throw new IllegalArgumentException("El costo de envío a decorar es obligatorio");
            }
            this.delegate = delegate;
        }
    }

    /** Añade el seguro del envío. */
    final class Insurance extends Decorator {
        static final long SURCHARGE = 2_500;

        public Insurance(ShippingCost delegate) {
            super(delegate);
        }

        @Override
        public long cost() {
            return delegate.cost() + SURCHARGE;
        }
    }

    /** Añade la entrega prioritaria. */
    final class Priority extends Decorator {
        static final long SURCHARGE = 7_000;

        public Priority(ShippingCost delegate) {
            super(delegate);
        }

        @Override
        public long cost() {
            return delegate.cost() + SURCHARGE;
        }
    }
}
