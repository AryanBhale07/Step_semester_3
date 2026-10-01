package main.java.inheritance_polymorphism.assignment_problems;

class PenaltyRaceEntry {
    protected double balanceDue;
    private double[] lateFeeHistory = new double[10];
    private int historyCount = 0;

    public PenaltyRaceEntry(double entryFee) {
        balanceDue = entryFee;
    }

    public void pay(double amount) {
        balanceDue -= amount;
    }

    protected void applyLateFee(double amount) {
        balanceDue += amount;
        lateFeeHistory[historyCount++] = amount;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public double[] getLateFeeHistory() {
        double[] copy = new double[historyCount];

        for (int i = 0; i < historyCount; i++)
            copy[i] = lateFeeHistory[i];

        return copy;
    }
}

class PenaltyRunnerEntry extends PenaltyRaceEntry {
    public PenaltyRunnerEntry(double entryFee) {
        super(entryFee);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class RacePenaltyAudit {
    public static void main(String[] args) {
        PenaltyRunnerEntry r =
                new PenaltyRunnerEntry(80);

        r.pay(30);
        r.applyLateFee(20);

        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        history[0] = 999;

        double[] newHistory = r.getLateFeeHistory();

        for (double fee : newHistory)
            System.out.print(fee + " ");
    }
}
