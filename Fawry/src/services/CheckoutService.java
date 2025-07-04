package services;

import entities.Product;
import entities.Shippable;
import models.Cart;
import models.Customer;

import java.util.*;

public class CheckoutService {

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Cannot checkout: Cart is empty.");
            return;
        }

        double subtotal = cart.calculateSubtotal();
        List<Shippable> shippableItems = cart.getShippableItems();

        double totalWeight = 0;
        for (Shippable item : shippableItems) {
            totalWeight += item.getWeight();
        }

        final double SHIPPING_RATE_PER_KG = 20.0;
        double shippingFee = totalWeight * SHIPPING_RATE_PER_KG;
        double total = subtotal + shippingFee;

        if (!customer.canAfford(total)) {
            System.out.println("❌ Cannot checkout: Insufficient balance.");
            return;
        }

        if (!shippableItems.isEmpty()) {
            System.out.println("** Shipment notice **");
            Map<String, Integer> shippingSummary = new HashMap<>();
            for (Shippable item : shippableItems) {
                shippingSummary.put(item.getName(), shippingSummary.getOrDefault(item.getName(), 0) + 1);
            }
            for (Map.Entry<String, Integer> entry : shippingSummary.entrySet()) {
                System.out.println(entry.getValue() + "x " + entry.getKey());
            }
            System.out.printf("Total package weight %.1fkg\n", totalWeight);
        }

        customer.deduct(total);

        System.out.println("\n** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            System.out.println(entry.getValue() + "x " + entry.getKey().getName() + " " +
                    (entry.getKey().getPrice() * entry.getValue()));
            entry.getKey().decreaseQuantity(entry.getValue());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.2f\n", subtotal);
        System.out.printf("Shipping %.2f\n", shippingFee);
        System.out.printf("Amount %.2f\n", total);
        System.out.printf("Remaining balance: %.2f\n", customer.getBalance());
        System.out.println("END.");

        cart.clear();
    }
}
