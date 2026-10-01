package main.java.oop_design_systems.class_problems;

abstract class Vehicle {
    private String name;
    private boolean available = true;

    public Vehicle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        available = false;
    }

    public void returnVehicle() {
        available = true;
    }

    public abstract double calculateCharge(int days);
}

class StandardCar extends Vehicle {
    public StandardCar(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 50;
    }
}

class LuxuryCar extends Vehicle {
    public LuxuryCar(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 100;
    }
}

class SUV extends Vehicle {
    public SUV(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 80;
    }
}

class Rental {
    private Vehicle vehicle;
    int days;
    private double amount;

    public Rental(Vehicle vehicle, int days) {
        this.vehicle = vehicle;
        this.days = days;
        this.amount = vehicle.calculateCharge(days);
    }

    public double getAmount() {
        return amount;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

class RentalService {
    public Rental rentVehicle(Vehicle vehicle, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println("Rental failed: Vehicle is already rented.");
            return null;
        }

        vehicle.rent();

        Rental rental = new Rental(vehicle, days);

        System.out.printf("%s rented for %d days.%n",
                vehicle.getName(), days);
        System.out.printf("Total charge: $%.2f%n",
                rental.getAmount());

        return rental;
    }

    public void returnVehicle(Rental rental) {
        rental.getVehicle().returnVehicle();

        System.out.println(
                rental.getVehicle().getName() +
                " returned. Now available."
        );
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        RentalService service = new RentalService();

        Vehicle luxury = new LuxuryCar("Luxury Car A");
        Vehicle standard = new StandardCar("Standard Car B");

        Rental r1 = service.rentVehicle(luxury, 3);
        Rental r2 = service.rentVehicle(standard, 5);

        service.returnVehicle(r1);
        service.returnVehicle(r2);
    }
}
