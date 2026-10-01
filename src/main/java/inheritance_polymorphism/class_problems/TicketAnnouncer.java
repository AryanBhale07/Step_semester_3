package main.java.inheritance_polymorphism.class_problems;

class AnnouncementEventTicket {
    protected double balanceDue;

    public AnnouncementEventTicket(double basePrice) {
        balanceDue = basePrice;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public String printTicket() {
        return "Standard | Balance: " + balanceDue;
    }
}

class AnnouncementWorkshopTicket extends AnnouncementEventTicket {
    private String track;

    public AnnouncementWorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public String printTicket() {
        return "Workshop | Track: " + track +
                " | Balance: " + balanceDue;
    }
}

public class TicketAnnouncer {
    static String batchPrint(AnnouncementEventTicket[] tickets) {
        StringBuilder result = new StringBuilder();

        for (AnnouncementEventTicket ticket : tickets) {
            result.append(ticket.printTicket());

            if (ticket instanceof AnnouncementWorkshopTicket) {
                AnnouncementWorkshopTicket workshop =
                        (AnnouncementWorkshopTicket) ticket;

                result.append(" [Track via downcast: ")
                      .append(workshop.getTrack())
                      .append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        AnnouncementEventTicket[] tickets = {
            new AnnouncementEventTicket(500),
            new AnnouncementWorkshopTicket(1200, "AI/ML")
        };

        System.out.println(batchPrint(tickets));
    }
}