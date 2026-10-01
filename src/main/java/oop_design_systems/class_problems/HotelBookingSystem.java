package main.java.oop_design_systems.class_problems;

import java.time.LocalDate;
import java.util.*;

abstract class Room {
    protected String name;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculatePrice(long days);
}

class StandardRoom extends Room {
    public StandardRoom(String name) {
        super(name);
    }

    public double calculatePrice(long days) {
        return days * 150;
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String name) {
        super(name);
    }

    public double calculatePrice(long days) {
        return days * 200;
    }
}

class SuiteRoom extends Room {
    public SuiteRoom(String name) {
        super(name);
    }

    public double calculatePrice(long days) {
        return days * 300;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Reservation {
    private Room room;
    Customer customer;
    private LocalDate start;
    private LocalDate end;
    private boolean cancelled;

    public Reservation(Room room, Customer customer,
                        LocalDate start, LocalDate end) {
        this.room = room;
        this.customer = customer;
        this.start = start;
        this.end = end;
    }

    public boolean overlaps(LocalDate otherStart,
                            LocalDate otherEnd) {
        return !otherEnd.isBefore(start) &&
               !otherStart.isAfter(end);
    }

    public void cancel() {
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public Room getRoom() {
        return room;
    }

    public double getPrice() {
        long days = java.time.temporal.ChronoUnit.DAYS
                .between(start, end);

        return room.calculatePrice(days);
    }
}

class Hotel {
    private List<Reservation> reservations = new ArrayList<>();

    public Reservation book(Room room, Customer customer,
                            LocalDate start, LocalDate end) {

        for (Reservation r : reservations) {
            if (!r.isCancelled() &&
                r.getRoom() == room &&
                r.overlaps(start, end)) {

                System.out.println(
                    "Booking failed: " + room.getName() +
                    " is not available."
                );

                return null;
            }
        }

        Reservation reservation =
                new Reservation(room, customer, start, end);

        reservations.add(reservation);

        System.out.println(room.getName() +
                " booked from " + start + " to " + end);

        System.out.printf("Total price: $%.2f%n",
                reservation.getPrice());

        return reservation;
    }

    public void cancel(Reservation reservation) {
        if (reservation == null)
            return;

        reservation.cancel();

        System.out.println(
                "Reservation for " +
                reservation.getRoom().getName() +
                " cancelled successfully."
        );
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Customer customer = new Customer("John");

        Room deluxe = new DeluxeRoom("Deluxe Room 101");
        Room standard = new StandardRoom("Standard Room 205");

        Reservation r1 = hotel.book(
                deluxe,
                customer,
                LocalDate.of(2024, 12, 1),
                LocalDate.of(2024, 12, 5)
        );

        hotel.book(
                standard,
                customer,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
        );

        hotel.book(
                deluxe,
                customer,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
        );

        hotel.cancel(r1);
    }
}
