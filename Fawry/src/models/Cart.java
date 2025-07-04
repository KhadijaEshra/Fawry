package models;

import entities.Product;
import entities.Shippable;

import java.util.*;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        if (product.isExpired()) {
            System.out.println("Can't add product: " + product.getName() + " is expired.");
            return;
        }

        if (product.getQuantity() < quantity) {
            System.out.println(" Can't add product: Not enough quantity available for " + product.getName());
            return;
        }

        items.put(product, items.getOrDefault(product, 0) + quantity);
        System.out.println(" Added " + quantity + " x " + product.getName() + " to cart.");
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public double calculateSubtotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public List<Shippable> getShippableItems() {
        List<Shippable> result = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            if (p instanceof Shippable) {
                for (int i = 0; i < entry.getValue(); i++) {
                    result.add((Shippable) p);
                }
            }
        }
        return result;
    }
}
