package main.java.strings.assignment_problems;

import java.util.*;

public class ProductInventoryCSVParser {

    static void parseInventoryRecord(String csvLine) {
        String[] data = csvLine.split(",");

        if (data.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Product: " + data[0]);
        System.out.println("SKU: " + data[1]);
        System.out.println("Qty: " + data[2]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter inventory record: ");
        String csvLine = sc.nextLine();

        parseInventoryRecord(csvLine);

        sc.close();
    }
}
