package main.java.inheritance_polymorphism.assignment_problems;

class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee,
                            String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    public void announce() {
        System.out.print("Elite Runner | Bib: " + bibNumber +
                " | Category: " + getCategory() +
                " | Sponsor Bonus: " + sponsorBonus +
                " | Balance: " + getBalanceDue());
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee,
                          int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public void announce() {
        System.out.print("Relay Team | Bib: " + bibNumber +
                " | Team Size: " + teamSize +
                " | Balance: " + getBalanceDue());
    }
}

public class RaceFamilyClassifier {
    static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry)
            return "Multilevel descendant (3 generations deep)";

        if (entry instanceof RelayTeamEntry)
            return "Hierarchical sibling (independent branch)";

        if (entry instanceof RunnerEntry)
            return "Single-level descendant";

        return "Base generation";
    }

    static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0;

        for (RaceEntry entry : entries)
            total += entry.getBalanceDue();

        return total;
    }

    public static void main(String[] args) {
        RunnerEntry r =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        EliteRunnerEntry e =
                new EliteRunnerEntry(
                    "BIB3001", 150,
                    "Elite Full Marathon", 500
                );

        RelayTeamEntry relay =
                new RelayTeamEntry("BIB4001", 300, 4);

        r.announce();
        System.out.println();

        e.announce();
        System.out.println();

        relay.announce();
        System.out.println();

        System.out.println(classifyGeneration(e));
        System.out.println(classifyGeneration(relay));

        System.out.println(
            getTotalBalanceDue(
                new RaceEntry[]{r, e, relay}
            )
        );
    }
}
