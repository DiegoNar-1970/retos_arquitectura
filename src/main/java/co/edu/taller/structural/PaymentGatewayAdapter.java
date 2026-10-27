package co.edu.taller.structural;

public class PaymentGatewayAdapter {
    public interface PaymentPort {
        String charge(String orderId, long pesos);
    }

    public static class LegacyGateway {
        public String chargeInCents(String id, long cents) {
            return "LEGACY:" + id + ":" + cents;
        }
    }

    private static final long CENTS_PER_PESO = 100;

    private final LegacyGateway legacy;

    public PaymentGatewayAdapter(LegacyGateway legacy) {
        if (legacy == null) {
            throw new IllegalArgumentException("La pasarela heredada es obligatoria");
        }
        this.legacy = legacy;
    }

    /**
     * Vista del adaptador como {@link PaymentPort}. Una clase no puede implementar
     * su propia interfaz anidada (herencia cíclica), por eso se expone así.
     */
    public PaymentPort asPort() {
        return this::charge;
    }

    /**
     * Adapter: expone la interfaz interna (pesos) y traduce la llamada
     * a la API heredada, que espera el importe en centavos.
     */
    public String charge(String orderId, long pesos) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("El identificador de la orden es obligatorio");
        }
        if (pesos <= 0) {
            throw new IllegalArgumentException("El importe debe ser mayor que cero");
        }
        try {
            return legacy.chargeInCents(orderId, Math.multiplyExact(pesos, CENTS_PER_PESO));
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("El importe es demasiado grande para convertirlo a centavos", e);
        }
    }
}
