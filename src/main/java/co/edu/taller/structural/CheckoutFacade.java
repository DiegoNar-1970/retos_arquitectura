package co.edu.taller.structural;

public class CheckoutFacade {
    public interface Inventory {
        boolean reserve(String sku, int quantity);
    }

    public interface Payment {
        boolean charge(String orderId, long amount);
    }

    private final Inventory inventory;
    private final Payment payment;

    public CheckoutFacade(Inventory i, Payment p) {
        inventory = i;
        payment = p;
    }

    public String checkout(String orderId, String sku, int quantity, long amount) {
        // TODO: validate, reserve inventory, charge payment, return confirmation.
        throw new UnsupportedOperationException("Implement Facade");
    }
}