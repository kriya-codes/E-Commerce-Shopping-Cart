import java.io.*;
import java.util.*;

public class FileManager {

    // Load products from file
    public static List<Product> loadProducts(String filename) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                int stock = Integer.parseInt(data[3]);

                products.add(new Product(id, name, price, stock));
            }

        } catch (Exception e) {
            System.out.println("Error loading products file.");
        }

        return products;
    }

    // Save order to file
    public static void saveOrder(String orderDetails) {
        try (FileWriter fw = new FileWriter("orders.txt", true)) {
            fw.write(orderDetails + "\n");
        } catch (Exception e) {
            System.out.println("Error saving order.");
        }
    }
}