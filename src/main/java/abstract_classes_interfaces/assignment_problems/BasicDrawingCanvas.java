package main.java.abstract_classes_interfaces.assignment_problems;

abstract class Shape {
    private static int counter = 1000;
    private final String shapeId;

    public Shape() {
        counter++;
        shapeId = "SHP-" + counter;
    }

    public abstract double calculateArea();

    public abstract void scale(double factor);

    public abstract void scale(double xFactor, double yFactor);

    public String getShapeId() {
        return shapeId;
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double factor) {
        radius *= factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        radius *= (xFactor + yFactor) / 2;
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double factor) {
        side *= factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        side *= (xFactor + yFactor) / 2;
    }
}

public class BasicDrawingCanvas {
    static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        SquareShape sq = new SquareShape(4.0);

        System.out.println(c.calculateArea());
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        printArea(c);

        // new Shape(); // Does not compile because Shape is abstract.
    }
}
