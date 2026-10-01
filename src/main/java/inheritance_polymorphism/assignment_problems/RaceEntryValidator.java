package main.java.inheritance_polymorphism.assignment_problems;

class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double balanceDue;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4)
            throw new IllegalArgumentException("Invalid bib number");

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
    }

    public void pay(double amount) {
        balanceDue -= amount;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public void announce() {
        System.out.print("Race Entry | Balance: " + balanceDue);
    }

    public static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered +
                " | Rejected: " + rejected;
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee,
                       String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public void announce() {
        System.out.print("Runner Entry | Bib: " + bibNumber +
                " | Category: " + category +
                " | Balance: " + balanceDue);
    }
}

public class RaceEntryValidator {
    public static void main(String[] args) {
        RunnerEntry r =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        r.pay(30);

        System.out.println(r.getBalanceDue());

        System.out.println(
            RaceEntry.registerBatch(
                new String[]{"BIB1", "B1", "BIB2"}, 80
            )
        );
    }
}