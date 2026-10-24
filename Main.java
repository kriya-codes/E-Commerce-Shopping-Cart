import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Product> products = FileManager.loadProducts("products.txt");

        Cart cart = new Cart();
        Discount currentDiscount = null;

        while (true) {
            System.out.println("\n------------------------------------------");
            System.out.println("MENU");
            System.out.println("------------------------------------------");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Apply Discount");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int option = sc.nextInt();

            switch (option) {

                case 1:
                    System.out.println("\n=== Available Products ===");
                    for (Product p : products) {
                        System.out.println(p.getId() + ". " + p.getName()
                                + " - $" + p.getPrice()
                                + " | Stock: " + p.getStock());
                    }
                    break;

                case 2:
                    System.out.print("Enter product ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();

                    Product selected = null;
                    for (Product p : products) {
                        if (p.getId() == id) {
                            selected = p;
                            break;
                        }
                    }

                    if (selected == null) {
                        System.out.println("Invalid product.");
                    } else if (!selected.isInStock(qty)) {
                        System.out.println("Not enough stock!");
                    } else {
                        cart.addItem(selected, qty);
                        selected.reduceStock(qty);
                        System.out.println("Item added to cart.");
                    }
                    break;

                case 3:
                    cart.showCart();
                    break;

                case 4:
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty. Add items first.");
                        break;
                    }

                    System.out.println("\n=== Apply Discount ===");
                    System.out.println("1. Percentage Discount");
                    System.out.println("2. Fixed Amount Discount");

                    int choice = sc.nextInt();

                    if (choice != 1 && choice != 2) {
                        System.out.println("Invalid choice!");
                        break;
                    }

                    if (choice == 1) {
                        System.out.print("Enter percentage: ");
                        double percent = sc.nextDouble();
                        currentDiscount = new PercentageDiscount(percent);
                    } else {
                        System.out.print("Enter amount: ");
                        double amount = sc.nextDouble();
                        currentDiscount = new FixedDiscount(amount);
                    }

                    System.out.println("Discount applied!");
                    break;

                case 5:
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty.");
                        break;
                    }

                OrderProcessor op = new OrderProcessor();
                double finalTotal = op.checkout(cart, currentDiscount);

                System.out.println("\n=== Checkout ===");
                System.out.println("Final Total: $" + finalTotal);

                // Save order
                String orderData = "Order Total: $" + finalTotal + " | Date: " + new Date();
                FileManager.saveOrder(orderData);

                System.out.println("Order saved successfully!");
                
                cart = new Cart();  // reset cart
                currentDiscount = null;
                break;

                case 6:
                    System.out.println("Thank you! Goodbye.");
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}