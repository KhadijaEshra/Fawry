package models;

public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean canAfford(double amount) {
        return balance >= amount;
    }

    public void deduct(double amount) {
        if (canAfford(amount)) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException(" Insufficient balance.");
        }
    }
}
