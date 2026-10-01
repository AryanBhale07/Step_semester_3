package main.java.constructors_keywords.class_problems;

class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0)
            throw new IllegalArgumentException("Fare cannot be negative");

        if (passengerCount <= 0)
            throw new IllegalArgumentException("Passenger count must be positive");

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    double[] fareBreakdown() {
        double[] result = new double[passengerCount];

        double share = Math.floor((totalFare / passengerCount) * 100) / 100;
        double used = 0;

        for (int i = 0; i < passengerCount - 1; i++) {
            result[i] = share;
            used += share;
        }

        result[passengerCount - 1] =
                Math.round((totalFare - used) * 100) / 100.0;

        return result;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
}

public class FareSplitter1 {
    public static void main(String[] args) {

        FareSplitter split =
                new FareSplitter("TRIP001", 100000, 3);

        double[] result = split.fareBreakdown();

        for (double value : result) {
            System.out.printf("%.2f ", value);
        }

        System.out.println();

        FareSplitter provisional =
                new FareSplitter("TRIP003");

        double[] result2 = provisional.fareBreakdown();

        for (double value : result2) {
            System.out.printf("%.1f ", value);
        }
    }
}
