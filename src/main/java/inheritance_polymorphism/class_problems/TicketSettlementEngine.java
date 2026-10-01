package main.java.inheritance_polymorphism.class_problems;

class SettlementEventTicket {
    protected double balanceDue;

    public SettlementEventTicket(double basePrice) {
        balanceDue = basePrice;
    }

    public void pay(double amount) {
        balanceDue -= amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5)
            return false;

        return code.charAt(0) == 'F'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isDigit(code.charAt(3))
                && Character.isUpperCase(code.charAt(4));
    }
}

class GroupTicket extends TicketSettlementEngine.EventTicket {
    private int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}

public class TicketSettlementEngine {
    private static int ticketsIssued = 0;

    public static class EventTicket extends SettlementEventTicket {
        private final String ticketId;

        public EventTicket(double basePrice) {
            super(basePrice);
            ticketsIssued++;
            ticketId = "TCK-" + (1000 + ticketsIssued);
        }

        public String getTicketId() {
            return ticketId;
        }

        public static int getTicketsIssued() {
            return ticketsIssued;
        }

        public static boolean isValidPromoCode(String code) {
            return SettlementEventTicket.isValidPromoCode(code);
        }

        public static String processNightlySettlement(EventTicket[] tickets) {
            int processed = 0;
            int nullSkipped = 0;
            int group = 0;
            int individual = 0;

            for (EventTicket ticket : tickets) {
                if (ticket == null) {
                    nullSkipped++;
                    continue;
                }

                processed++;

                if (ticket instanceof GroupTicket)
                    group++;
                else
                    individual++;
            }

            return processed + " processed | " +
                    nullSkipped + " null skipped | " +
                    group + " group | " +
                    individual + " individual";
        }
    }
}
