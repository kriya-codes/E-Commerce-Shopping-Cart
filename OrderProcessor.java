public class OrderProcessor {

    public double checkout(Cart cart, Discount discount) {
        double subtotal = cart.calculateTotal();

        double discountedTotal = subtotal;

        if (discount != null) {
            discountedTotal = discount.applyDiscount(subtotal);
        }

        double tax = discountedTotal * 0.1;
        double shipping = 10;

        double finalTotal = discountedTotal + tax + shipping;

        // Bill breakdown
        System.out.println("\n=== Bill Details ===");
        System.out.printf("Subtotal: $%.2f\n", subtotal);
        System.out.printf("After Discount: $%.2f\n", discountedTotal);
        System.out.printf("Tax (10%%): $%.2f\n", tax);
        System.out.printf("Shipping: $%.2f\n", shipping);
        System.out.printf("Final Total: $%.2f\n", finalTotal);

        return finalTotal;
    }
}