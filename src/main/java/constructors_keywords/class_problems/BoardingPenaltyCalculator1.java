package main.java.constructors_keywords.class_problems;

final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    final double calculatePenalty(double ticketFare, int minutesLate) {

        if (ticketFare < 0)
            throw new IllegalArgumentException("Ticket fare cannot be negative");

        if (minutesLate < 0)
            throw new IllegalArgumentException("Minutes late cannot be negative");

        if (minutesLate == 0)
            return 0;

        int firstTier = Math.min(minutesLate, 5);
        int secondTier = Math.min(Math.max(minutesLate - 5, 0), 10);
        int thirdTier = Math.max(minutesLate - 15, 0);

        double penalty =
                ticketFare * 0.005 * firstTier
                + ticketFare * 0.01 * secondTier
                + ticketFare * 0.02 * thirdTier;

        double minimumPenalty =
                ticketFare * minimumPenaltyPercent / 100;

        return Math.max(penalty, minimumPenalty);
    }
}

public class BoardingPenaltyCalculator1 {
    public static void main(String[] args) {

        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1);

        System.out.println("Rs " +
                calculator.calculatePenalty(1000, 0));

        System.out.println("Rs " +
                calculator.calculatePenalty(1000, 1));

        System.out.println("Rs " +
                calculator.calculatePenalty(1000, 16));
    }
}
