package main.java.oops.assignment_problems;

class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        if (daysOverdue > 0)
            return daysOverdue * 5;
        return 0;
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    // Static because this calculates the total for many BookIssue objects.
    // fineAmount() belongs to one particular BookIssue object.
    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;

        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }

        return total;
    }
}

public class LibraryFineSystem {
    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Ravi", 18),
            new BookIssue("Effective Java", "Anitha", 5),
            new BookIssue("Refactoring", "Karthik", 0),
            new BookIssue("DSA Handbook", "Meera", 21),
            new BookIssue("Design Patterns", "Suresh", 9)
        };

        for (BookIssue issue : issues) {
            if (issue.isSeverelyOverdue())
                System.out.println(issue.title + " - " + issue.daysOverdue + " days - Severely overdue");
            else
                System.out.println(issue.title + " - " + issue.daysOverdue + " days - OK");
        }

        System.out.println("Total fine collected: Rs " +
                BookIssue.totalFineCollected(issues));
    }
}