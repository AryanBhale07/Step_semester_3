package main.java.constructors_keywords.class_problems;

class BusTicketAccount {
    private String bookingId;
    private double ticketFare;

    static double minimumPenaltyPercent;

    static {
        minimumPenaltyPercent = 1.0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (ticketFare < 0)
            throw new IllegalArgumentException("Fare cannot be negative");

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0)
            throw new IllegalArgumentException("Minutes late cannot be negative");

        if (minutesLate == 0)
            return 0;

        double penalty;

        int first = Math.min(minutesLate, 5);
        int second = Math.min(Math.max(minutesLate - 5, 0), 10);
        int third = Math.max(minutesLate - 15, 0);

        penalty = ticketFare * 0.005 * first
                + ticketFare * 0.01 * second
                + ticketFare * 0.02 * third;

        double floor =
                ticketFare * minimumPenaltyPercent / 100;

        return Math.max(penalty, floor);
    }

    void processAccount(BusTicketAccount account,
                        double amount,
                        int minutesLate) {

        if (account == null)
            return;

        double penalty = account.calculatePenalty(minutesLate);

        if (account instanceof SleeperAccount) {
            penalty = penalty * 0.5;
        }

        System.out.println("Booking " + account.bookingId +
                " | Amount: Rs " + amount +
                " | Penalty: Rs " + penalty);
    }

    static void processBatch(BusTicketAccount[] accounts,
                             double[] amounts,
                             int[] minutesLateArray) {

        if (accounts.length != amounts.length ||
            accounts.length != minutesLateArray.length) {

            System.out.println("Batch rejected: array lengths do not match");
            return;
        }

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;
        double totalPenalty = 0;

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double penalty =
                    accounts[i].calculatePenalty(minutesLateArray[i]);

            if (accounts[i] instanceof SleeperAccount) {
                penalty = penalty * 0.5;
                sleeper++;
            } else {
                regular++;
            }

            totalPenalty += penalty;
            processed++;
        }

        System.out.println(processed + " processed | " +
                nullSkipped + " null skipped | " +
                sleeper + " sleeper | " +
                regular + " regular | " +
                "grand total penalties = Rs " + totalPenalty);
    }
}

class SleeperAccount extends BusTicketAccount {

    SleeperAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }
}

public class FleetReconciliation {
    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
            new SleeperAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
            1200, 900, 700
        };

        int[] minutesLate = {
            10, 5, 0
        };

        BusTicketAccount.processBatch(
                accounts, amounts, minutesLate);
    }
}
