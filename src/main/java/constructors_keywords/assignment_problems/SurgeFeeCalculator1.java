package main.java.constructors_keywords.assignment_problems;

final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    final double calculateSurgeFee(double orderValue,
                                   int delayMinutes) {

        if (orderValue < 0)
            throw new IllegalArgumentException(
                    "Order value cannot be negative");

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

        double minimumFee =
                orderValue * minimumSurgePercent / 100;

        return Math.max(fee, minimumFee);
    }
}

public class SurgeFeeCalculator1 {
    public static void main(String[] args) {

        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1);

        System.out.println("Rs " +
                calculator.calculateSurgeFee(500, 0));

        System.out.println("Rs " +
                calculator.calculateSurgeFee(500, 1));

        System.out.println("Rs " +
                calculator.calculateSurgeFee(500, 16));
    }
}
