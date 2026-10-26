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

    private final LegacyGateway legacy;

    public PaymentGatewayAdapter(LegacyGateway legacy) {
        this.legacy = legacy;
    }

    public String charge(String orderId, long pesos) { // TODO: validate and convert pesos to cents
        throw new UnsupportedOperationException("Implement Adapter");
    }
}