package main.java.inheritance_polymorphism.class_problems;

class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double balanceDue;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid attendee ID");

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public void pay(double amount) {
        balanceDue -= amount;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public void printTicket() {
        System.out.print("Standard Event Ticket | Balance Due: " + balanceDue);
    }

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;

        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public void printTicket() {
        System.out.print("Workshop Ticket | Track: " + track +
                " | Balance Due: " + balanceDue);
    }
}

public class TicketHierarchyValidator {
    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println(w.getBalanceDue());

        System.out.println(
            EventTicket.registerBatch(
                new String[]{"STU1", "ST1", "STU2", " ", "STU3"}, 500
            )
        );
    }
}
