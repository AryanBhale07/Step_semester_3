package main.java.inheritance_polymorphism.assignment_problems;

class AnnounceRaceEntry {
    protected double balanceDue;

    public AnnounceRaceEntry(double entryFee) {
        balanceDue = entryFee;
    }

    public String announce() {
        return "Race Entry | Balance: " + balanceDue;
    }
}

class AnnounceRunnerEntry extends AnnounceRaceEntry {
    private String bibNumber;
    private String category;

    public AnnounceRunnerEntry(double entryFee,
                               String bibNumber,
                               String category) {
        super(entryFee);
        this.bibNumber = bibNumber;
        this.category = category;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + bibNumber +
                " | Category: " + category +
                " | Balance: " + balanceDue;
    }
}

class AnnounceRelayTeamEntry extends AnnounceRaceEntry {
    private String bibNumber;
    private int teamSize;

    public AnnounceRelayTeamEntry(double entryFee,
                                  String bibNumber,
                                  int teamSize) {
        super(entryFee);
        this.bibNumber = bibNumber;
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + bibNumber +
                " | Team Size: " + teamSize +
                " | Balance: " + balanceDue;
    }
}

public class RaceDayAnnouncer {
    static String announceAll(AnnounceRaceEntry[] entries) {
        StringBuilder result = new StringBuilder();

        for (AnnounceRaceEntry entry : entries) {
            result.append(entry.announce());

            if (entry instanceof AnnounceRelayTeamEntry) {
                AnnounceRelayTeamEntry relay =
                        (AnnounceRelayTeamEntry) entry;

                result.append(" [Team size via downcast: ")
                      .append(relay.getTeamSize())
                      .append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }
}
