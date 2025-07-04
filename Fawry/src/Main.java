import entities.*;
import models.*;
import services.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ExpirableShippableProduct cheese = new ExpirableShippableProduct("Cheese", 100.0, 10, LocalDate.of(2025, 12, 31), 0.2);
        ExpirableShippableProduct biscuits = new ExpirableShippableProduct("Biscuits", 150.0, 5, LocalDate.of(2025, 12, 31), 0.7);
        ShippableProduct tv = new ShippableProduct("TV", 4000.0, 2, 5.0);
        Product scratchCard = new Product("Scratch Card", 50.0, 100);

        Customer customer = new Customer("Khadija", 5000.0);
        Cart cart = new Cart();

        cart.addProduct(cheese, 2);
        cart.addProduct(biscuits, 1);
        cart.addProduct(tv, 1);
        cart.addProduct(scratchCard, 2);

        CheckoutService.checkout(customer, cart);
    }
}
