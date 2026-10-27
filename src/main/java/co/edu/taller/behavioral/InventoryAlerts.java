package co.edu.taller.behavioral;

import java.util.*;

public class InventoryAlerts {
    public interface Listener {
        void onLowStock(String sku, int quantity);
    }

    private final int threshold;
    private final List<Listener> listeners = new ArrayList<>();

    public InventoryAlerts(int threshold) {
        this.threshold = threshold;
    }

    public void subscribe(Listener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("El suscriptor es obligatorio");
        }
        listeners.add(listener);
    }

    /**
     * Observer: notifica a todos los suscriptores cuando el stock
     * llega o cae por debajo del umbral configurado.
     */
    public void update(String sku, int quantity) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("El SKU es obligatorio");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        if (quantity <= threshold) {
            // Copia defensiva: un suscriptor puede (des)suscribirse durante la notificación.
            for (Listener listener : List.copyOf(listeners)) {
                listener.onLowStock(sku, quantity);
            }
        }
    }
}
