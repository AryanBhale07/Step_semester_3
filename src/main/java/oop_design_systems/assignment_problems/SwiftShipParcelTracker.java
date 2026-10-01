package main.java.oop_design_systems.assignment_problems;

import java.util.*;

interface ShippingType {
    double calculateCharge(double weight);
    String getName();
}

class StandardShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 40 + weight * 10;
    }

    public String getName() {
        return "Standard";
    }
}

class ExpressShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 80 + weight * 15;
    }

    public String getName() {
        return "Express";
    }
}

class FragileShipping implements ShippingType {
    private ShippingType standard = new StandardShipping();

    public double calculateCharge(double weight) {
        return standard.calculateCharge(weight) + 50;
    }

    public String getName() {
        return "Fragile";
    }
}

interface NotificationChannel {
    void notify(String message);
}

class SmsChannel implements NotificationChannel {
    public void notify(String message) {
        System.out.println("[SMS] " + message);
    }
}

class EmailChannel implements NotificationChannel {
    public void notify(String message) {
        System.out.println("[Email] " + message);
    }
}

enum ParcelStatus {
    BOOKED,
    PICKED_UP,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED
}

class Parcel {
    private String id;
    private double weight;
    private ShippingType shippingType;
    private ParcelStatus status = ParcelStatus.BOOKED;
    private List<NotificationChannel> channels =
            new ArrayList<>();

    public Parcel(String id, double weight,
                  ShippingType shippingType) {
        this.id = id;
        this.weight = weight;
        this.shippingType = shippingType;
    }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    public void notifyChannels() {
        for (NotificationChannel channel : channels)
            channel.notify(id + " is now " + status);
    }

    public void moveTo(ParcelStatus next) {
        if (next.ordinal() != status.ordinal() + 1) {
            System.out.println(
                "Invalid transition: " +
                status + " → " + next +
                " is not allowed."
            );
            return;
        }

        status = next;
        notifyChannels();
    }

    public void cancel() {
        if (status != ParcelStatus.BOOKED) {
            System.out.println(
                "Cancellation failed: " + id +
                " can be cancelled only while BOOKED."
            );
            return;
        }

        System.out.println(id + " cancelled.");
    }

    public double getCharge() {
        return shippingType.calculateCharge(weight);
    }

    public String getId() {
        return id;
    }

    public String getShippingName() {
        return shippingType.getName();
    }

    public double getWeight() {
        return weight;
    }
}

public class SwiftShipParcelTracker {
    public static void main(String[] args) {
        Parcel parcel = new Parcel(
            "P101",
            2,
            new ExpressShipping()
        );

        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());

        System.out.println(
            "Parcel " + parcel.getId() +
            " booked (" +
            parcel.getShippingName() +
            ", " + parcel.getWeight() + " kg)."
        );

        System.out.printf("Charge: ₹%.2f%n",
                parcel.getCharge());

        parcel.notifyChannels();

        parcel.moveTo(ParcelStatus.PICKED_UP);

        parcel.cancel();

        parcel.moveTo(ParcelStatus.IN_TRANSIT);

        parcel.moveTo(ParcelStatus.DELIVERED);
    }
}
