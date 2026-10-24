public class FixedDiscount extends Discount {
    private double amount;

    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double applyDiscount(double total) {
        return total - amount;
    }
}