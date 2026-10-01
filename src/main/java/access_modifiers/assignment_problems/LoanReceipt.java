package main.java.access_modifiers.assignment_problems;

import java.util.Arrays;

class LoanReceiptModel {
    private final String memberId;
    private final String[] bookIds;

    static int totalReceipts;

    static {
        totalReceipts = 0;
    }

    public LoanReceiptModel(
            String memberId, String[] bookIds) {

        if (memberId == null || memberId.trim().isEmpty())
            throw new IllegalArgumentException("Invalid member ID");

        if (bookIds == null)
            throw new IllegalArgumentException("Invalid book list");

        for (String id : bookIds) {
            if (id == null || !id.matches("BK-\\d{3}"))
                throw new IllegalArgumentException(
                        "Invalid book ID");
        }

        this.memberId = memberId;
        this.bookIds =
                Arrays.copyOf(bookIds, bookIds.length);

        totalReceipts++;
    }

    String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    LoanReceiptModel withCorrectedBookId(
            int index, String newId) {

        if (index < 0 || index >= bookIds.length)
            throw new IllegalArgumentException("Invalid index");

        if (newId == null || !newId.matches("BK-\\d{3}"))
            throw new IllegalArgumentException("Invalid book ID");

        String[] copy = getBookIds();
        copy[index] = newId;

        return new LoanReceiptModel(memberId, copy);
    }
}

class ReferenceOnlyLoanReceipt
        extends LoanReceiptModel {

    ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
    }
}

public class LoanReceipt {

    static String processNightlyCirculation(
            LoanReceiptModel[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceiptModel receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt)
                referenceOnly++;
            else
                regular++;
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                referenceOnly + " reference-only | " +
                regular + " regular";
    }

    public static void main(String[] args) {

        LoanReceiptModel[] receipts = {
            new ReferenceOnlyLoanReceipt(
                "LIB-001",
                new String[]{"BK-200"},
                "Reading Room 3"),

            null,

            new LoanReceiptModel(
                "LIB-002",
                new String[]{"BK-201"})
        };

        System.out.println(
                processNightlyCirculation(receipts));
    }
}
