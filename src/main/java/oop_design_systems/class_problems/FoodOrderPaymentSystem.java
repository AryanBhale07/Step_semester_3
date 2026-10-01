package main.java.oop_design_systems.class_problems;

import java.util.*;

class FoodItem {
    private String name;
    private double price;

    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class LineItem {
    private FoodItem item;
    private int quantity;

    public LineItem(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotal() {
        return item.getPrice() * quantity;
    }

    public String getName() {
        return item.getName();
    }

    public int getQuantity() {
        return quantity;
    }
}

interface IPaymentMethod {
    boolean pay(double amount);
    String getName();
}

class CreditCardPayment implements IPaymentMethod {
    public boolean pay(double amount) {
        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class DigitalWalletPayment implements IPaymentMethod {
    public boolean pay(double amount) {
        return false;
    }

    public String getName() {
        return "Digital Wallet";
    }
}

class Customer {
    String name;

    public Customer(String name) {
        this.name = name;
    }
}

class Order {
    private static int counter = 123;
    private int orderId;
    private List<LineItem> items = new ArrayList<>();
    String status = "Pending Payment";

    public Order() {
        orderId = counter++;
        System.out.println("Order created.");
    }

    public void addItem(FoodItem item, int quantity) {
        items.add(new LineItem(item, quantity));

        System.out.println(
            "Added " + item.getName() +
            " (Qty " + quantity + ")"
        );
    }

    public void place(IPaymentMethod payment) {
        if (items.isEmpty()) {
            System.out.println(
                "Cannot place order: Order must contain at least one item."
            );
            return;
        }

        System.out.println("Order placed successfully.");

        double total = 0;

        for (LineItem item : items)
            total += item.getTotal();

        if (payment.pay(total)) {
            status = "Paid";

            System.out.println(
                "Payment via " +
                payment.getName() +
                " successful."
            );

            System.out.println(
                "Order status: Paid."
            );

            System.out.println(
                "Notification: Order #" +
                orderId + " placed and paid."
            );
        } else {
            System.out.println(
                "Payment via " +
                payment.getName() +
                " failed."
            );

            System.out.println(
                "Order status: Pending Payment."
            );

            System.out.println(
                "Notification: Order #" +
                orderId +
                " placed, awaiting payment."
            );
        }
    }
}

public class FoodOrderPaymentSystem {
    public static void main(String[] args) {
        FoodItem pizza = new FoodItem("Pizza", 200);
        FoodItem soda = new FoodItem("Soda", 50);
        FoodItem burger = new FoodItem("Burger", 150);

        Order order1 = new Order();

        order1.addItem(pizza, 2);
        order1.addItem(soda, 1);

        order1.place(new CreditCardPayment());

        Order order2 = new Order();

        order2.addItem(burger, 1);

        order2.place(new DigitalWalletPayment());
    }
}
