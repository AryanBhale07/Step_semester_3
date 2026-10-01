package main.java.access_modifiers.assignment_problems;

public class BookCopyCirculationGuard {
    private int copiesTotal;
    private int copiesAvailable;

    BookCopyCirculationGuard(int copiesTotal) {

        if (copiesTotal <= 0)
            throw new IllegalArgumentException(
                    "Copies total must be positive");

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    void checkOut() {

        if (copiesAvailable > 0)
            copiesAvailable--;
    }

    void checkIn() {

        if (copiesAvailable < copiesTotal)
            copiesAvailable++;
    }

    int getCopiesAvailable() {
        return copiesAvailable;
    }

    public static void main(String[] args) {

        BookCopyCirculationGuard b =
                new BookCopyCirculationGuard(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();

        System.out.println(b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();

        System.out.println(b.getCopiesAvailable());
    }
}
