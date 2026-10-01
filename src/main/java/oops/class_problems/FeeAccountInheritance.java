package main.java.oops.class_problems;

class FeeAccount {
    String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        } else {
            System.out.println("Payment rejected");
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    void payInTwoInstallments(double amount) {
        pay(amount);
        pay(amount);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee,
                          double amountPaid, double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() - (getDue() * scholarshipPercent / 100);
    }
}

public class FeeAccountInheritance {
    public static void main(String[] args) {

        FeeAccount plain =
                new FeeAccount("101", 150000, 150000);

        FeeAccount hostel =
                new HostelFeeAccount("102", 200000, 60000);

        FeeAccount scholarship =
                new ScholarshipFeeAccount("103", 180000, 0, 20);

        if (plain instanceof HostelFeeAccount) {
            ((HostelFeeAccount) plain).payInTwoInstallments(10000);
        } else if (plain instanceof ScholarshipFeeAccount) {
            System.out.println("Scholarship account");
        } else {
            System.out.println("Plain account due: Rs " +
                    plain.getDue());
        }

        if (hostel instanceof HostelFeeAccount) {
            System.out.println("Hostel account due: Rs " +
                    hostel.getDue());
        }

        if (scholarship instanceof ScholarshipFeeAccount) {
            System.out.println("Scholarship account effective due: Rs " +
                    ((ScholarshipFeeAccount) scholarship).effectiveDue());
        }
    }
}
