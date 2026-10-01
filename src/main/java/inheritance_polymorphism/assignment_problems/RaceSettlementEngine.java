package main.java.inheritance_polymorphism.assignment_problems;

class SettlementRaceEntry {
    protected double balanceDue;
    String entryCode;

    private static int bibCounter = 0;

    public SettlementRaceEntry(String bibNumber, double entryFee) {
        balanceDue = entryFee;

        bibCounter++;
        entryCode = "ENT-" + (1000 + bibCounter);
    }

    public void pay(double amount) {
        balanceDue -= amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5)
            return false;

        return code.charAt(0) == 'M'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isDigit(code.charAt(3))
                && Character.isUpperCase(code.charAt(4));
    }

    public static int getBibCounter() {
        return bibCounter;
    }
}

class SettlementRelayTeamEntry extends SettlementRaceEntry {
    int teamSize;

    public SettlementRelayTeamEntry(String bibNumber,
                                    double entryFee,
                                    int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }
}

public class RaceSettlementEngine {
    static String settleNight(SettlementRaceEntry[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (SettlementRaceEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof SettlementRelayTeamEntry)
                relay++;
            else
                individual++;
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                relay + " relay | " +
                individual + " individual";
    }
}
