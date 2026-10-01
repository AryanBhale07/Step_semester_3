package main.java.oop_design_systems.assignment_problems;

import java.util.*;

interface PricingPlan {
    double calculatePrice(double originalPrice);
}

class DayScholarPlan implements PricingPlan {
    public double calculatePrice(double originalPrice) {
        return originalPrice;
    }
}

class HostellerPlan implements PricingPlan {
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.90;
    }
}

class StaffPlan implements PricingPlan {
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.80;
    }
}

class Transaction {
    private double amount;
    private String description;

    public Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

class SmartCard {
    private String cardId;
    private PricingPlan plan;
    private double balance;
    private boolean blocked;

    private List<Transaction> transactions =
            new ArrayList<>();

    private Set<String> refundedPurchases =
            new HashSet<>();

    public SmartCard(String cardId, PricingPlan plan) {
        this.cardId = cardId;
        this.plan = plan;
    }

    public void topUp(double amount) {
        if (blocked) {
            System.out.println("Top-up rejected: Card is blocked.");
            return;
        }

        if (amount < 100) {
            System.out.println(
                "Top-up rejected: Minimum top-up is ₹100."
            );
            return;
        }

        if (balance + amount > 5000) {
            System.out.println(
                "Top-up rejected: Maximum balance is ₹5000."
            );
            return;
        }

        balance += amount;

        transactions.add(
            new Transaction(amount, "Top-up")
        );

        System.out.printf(
            "%s topped up with ₹%.2f. Balance: ₹%.2f%n",
            cardId, amount, balance
        );
    }

    public void purchase(String item, double price) {
        if (blocked) {
            System.out.println(
                "Purchase failed: Card is blocked."
            );
            return;
        }

        double charged = plan.calculatePrice(price);

        if (charged > balance) {
            System.out.printf(
                "Purchase failed: Insufficient balance " +
                "(required ₹%.2f, available ₹%.2f)%n",
                charged, balance
            );
            return;
        }

        balance -= charged;

        transactions.add(
            new Transaction(-charged, item)
        );

        System.out.printf(
            "%s purchased for ₹%.2f. Balance: ₹%.2f%n",
            item, charged, balance
        );
    }

    public void refund(String item, double amount) {
        if (refundedPurchases.contains(item)) {
            System.out.println(
                "Refund rejected: " + item +
                " has already been refunded."
            );
            return;
        }

        balance += amount;

        transactions.add(
            new Transaction(amount, "Refund " + item)
        );

        refundedPurchases.add(item);

        System.out.printf(
            "Refund of ₹%.2f for %s processed. Balance: ₹%.2f%n",
            amount, item, balance
        );
    }

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }

    public void miniStatement() {
        System.out.print(
            "Mini-statement for " + cardId + ": "
        );

        double total = 0;

        for (Transaction t : transactions) {
            System.out.printf("%+.2f, ", t.getAmount());
            total += t.getAmount();
        }

        System.out.printf("= ₹%.2f%n", total);
    }
}

public class CampusCanteenSmartCard {
    public static void main(String[] args) {
        SmartCard card =
                new SmartCard(
                    "C-2045",
                    new HostellerPlan()
                );

        card.topUp(500);

        card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);

        card.purchase("Items", 400);

        card.refund("Veg Thali", 108);

        card.refund("Veg Thali", 108);

        card.miniStatement();
    }
}
