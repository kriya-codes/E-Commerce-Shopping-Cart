import java.util.*;

public class Cart {
    private List<Product> items = new ArrayList<>();

    public void addItem(Product p, int quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(p);
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\n=== Your Cart ===");
        for (Product p : items) {
            System.out.println(p.getName() + " - $" + p.getPrice());
        }
    }
}