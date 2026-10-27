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
        if (i == null || p == null) {
            throw new IllegalArgumentException("Inventario y pago son obligatorios");
        }
        inventory = i;
        payment = p;
    }

    /**
     * Facade: una única operación de alto nivel que coordina inventario y pago.
     *
     * @return la confirmación {@code CONFIRMED:<orderId>}
     * @throws IllegalArgumentException si algún dato de entrada es inválido
     * @throws IllegalStateException    si no hay inventario o el pago es rechazado
     */
    public String checkout(String orderId, String sku, int quantity, long amount) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("El identificador de la orden es obligatorio");
        }
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("El SKU es obligatorio");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("El importe debe ser mayor que cero");
        }
        if (!inventory.reserve(sku, quantity)) {
            throw new IllegalStateException("Inventario insuficiente para el SKU " + sku);
        }
        if (!payment.charge(orderId, amount)) {
            throw new IllegalStateException("Pago rechazado para la orden " + orderId);
        }
        return "CONFIRMED:" + orderId;
    }
}
