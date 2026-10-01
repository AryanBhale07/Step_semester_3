package main.java.constructors_keywords.class_problems;

import java.util.HashSet;

class BusTicket {
    private String passengerName;
    String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().isEmpty())
            throw new IllegalArgumentException("Invalid passenger name");

        if (destination == null || destination.trim().isEmpty())
            throw new IllegalArgumentException("Invalid destination");

        if (!passengerName.matches("[A-Za-z ]+"))
            throw new IllegalArgumentException("Invalid passenger name");

        this.passengerName = passengerName;
        this.destination = destination;
        this.checkedIn = false;
    }

    void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Checked in: " + passengerName);
        } else {
            System.out.println("Already checked in: " + passengerName);
        }
    }

    static void processBatch(String[][] rawBookings) {
        HashSet<String> accepted = new HashSet<>();

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        for (String[] booking : rawBookings) {
            try {
                if (booking == null || booking.length < 2)
                    throw new IllegalArgumentException();

                BusTicket ticket =
                        new BusTicket(booking[0], booking[1]);

                String key = booking[0].trim().toLowerCase()
                        + "|" + booking[1].trim().toLowerCase();

                if (accepted.contains(key)) {
                    duplicates++;
                } else {
                    accepted.add(key);
                    valid++;
                }

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicates);
    }
}

public class BusTicketValidator {
    public static void main(String[] args) {

        String[][] bookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        BusTicket.processBatch(bookings);
    }
}