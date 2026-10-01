package main.java.constructors_keywords.assignment_problems;

class DeliveryAccount {
    private String studentId;
    private double orderValue;

    static double minimumSurgePercent;

    static {
        minimumSurgePercent = 1.0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        if (orderValue < 0)
            throw new IllegalArgumentException(
                    "Order value cannot be negative");

        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0);
    }

    final double calculateSurgeFee(int delayMinutes) {

        if (delayMinutes < 0)
            throw new IllegalArgumentException(
                    "Delay cannot be negative");

        if (delayMinutes == 0)
            return 0;

        int first = Math.min(delayMinutes, 5);
        int second = Math.min(
                Math.max(delayMinutes - 5, 0), 10);
        int third = Math.max(delayMinutes - 15, 0);

        double fee =
                orderValue * 0.005 * first
                + orderValue * 0.01 * second
                + orderValue * 0.02 * third;

        double floor =
                orderValue * minimumSurgePercent / 100;

        return Math.max(fee, floor);
    }

    void processAccount(DeliveryAccount account,
                        double amount,
                        int delayMinutes) {

        if (account == null)
            return;

        double fee =
                account.calculateSurgeFee(delayMinutes);

        if (account instanceof PremiumAccount)
            fee = fee * 0.5;

        System.out.println(
                account.studentId +
                " | Amount: Rs " + amount +
                " | Surge fee: Rs " + fee);
    }

    static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        if (accounts.length != amounts.length ||
            accounts.length != delayMinutesArray.length) {

            System.out.println(
                    "Batch rejected: array lengths do not match");
            return;
        }

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double totalFee = 0;

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double fee =
                    accounts[i].calculateSurgeFee(
                            delayMinutesArray[i]);

            if (accounts[i] instanceof PremiumAccount) {
                fee = fee * 0.5;
                premium++;
            } else {
                regular++;
            }

            totalFee += fee;
            processed++;
        }

        System.out.println(
                processed + " processed | " +
                nullSkipped + " null skipped | " +
                premium + " premium | " +
                regular + " regular | " +
                "grand total surge fees = Rs " +
                totalFee);
    }
}

class PremiumAccount extends DeliveryAccount {

    PremiumAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
}

public class KitchenReconciliation {
    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
            new PremiumAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
            500, 400, 300
        };

        int[] delays = {
            10, 5, 0
        };

        DeliveryAccount.processBatch(
                accounts, amounts, delays);
    }
}
