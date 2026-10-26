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

    public void subscribe(Listener listener) { // TODO
        throw new UnsupportedOperationException("Implemente Observer");
    }

    public void update(String sku, int quantity) { // TODO
        throw new UnsupportedOperationException("Implemente Observer");
    }
}