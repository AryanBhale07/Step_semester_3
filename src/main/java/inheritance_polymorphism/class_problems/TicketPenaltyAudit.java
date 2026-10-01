package main.java.inheritance_polymorphism.class_problems;

class PenaltyEventTicket {
    protected double balanceDue;
    private double[] lateFeeHistory = new double[10];
    private int historyCount = 0;

    public PenaltyEventTicket(double basePrice) {
        balanceDue = basePrice;
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

class PenaltyWorkshopTicket extends PenaltyEventTicket {
    public PenaltyWorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class TicketPenaltyAudit {
    public static void main(String[] args) {
        PenaltyWorkshopTicket w = new PenaltyWorkshopTicket(1200);

        w.pay(1200);
        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        history[0] = 999;

        double[] newHistory = w.getLateFeeHistory();

        for (double fee : newHistory)
            System.out.print(fee + " ");
    }
}
