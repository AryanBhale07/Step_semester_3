package main.java.inheritance_polymorphism.class_problems;

class PremiumWorkshopTicket extends WorkshopTicket {
    private double kitFee;

    public PremiumWorkshopTicket(String attendeeId, double basePrice,
                                 String track, double kitFee) {
        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    @Override
    public void printTicket() {
        System.out.print("Premium Workshop Ticket | Track: " +
                getTrack() + " | Kit Fee: " + kitFee +
                " | Balance Due: " + getBalanceDue());
    }
}

class HackathonTicket extends EventTicket {
    private String teamName;

    public HackathonTicket(String attendeeId, double basePrice,
                           String teamName) {
        super(attendeeId, basePrice);
        this.teamName = teamName;
    }

    @Override
    public void printTicket() {
        System.out.print("Hackathon Ticket | Team: " + teamName +
                " | Balance Due: " + getBalanceDue());
    }
}

public class TicketFamilyClassifier {
    static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket)
            return "Multilevel descendant (3 generations deep)";

        if (ticket instanceof HackathonTicket)
            return "Hierarchical sibling (independent branch)";

        if (ticket instanceof WorkshopTicket)
            return "Single-level descendant";

        return "Base generation";
    }

    static double getTotalBalanceDue(EventTicket[] tickets) {
        double total = 0;

        for (EventTicket ticket : tickets)
            total += ticket.getBalanceDue();

        return total;
    }

    public static void main(String[] args) {
        EventTicket a = new EventTicket("STU1", 500);
        WorkshopTicket b = new WorkshopTicket("STU2", 1200, "AI/ML");
        PremiumWorkshopTicket c =
                new PremiumWorkshopTicket("STU3", 2000, "Cloud Native", 300);
        HackathonTicket d =
                new HackathonTicket("STU4", 800, "Byte Force");

        a.printTicket();
        System.out.println();

        b.printTicket();
        System.out.println();

        c.printTicket();
        System.out.println();

        d.printTicket();
        System.out.println();

        System.out.println(classifyGeneration(c));
        System.out.println(classifyGeneration(d));

        System.out.println(
            getTotalBalanceDue(new EventTicket[]{a, b, c, d})
        );
    }
}
