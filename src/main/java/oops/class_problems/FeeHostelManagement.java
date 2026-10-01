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

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {
        if (occupied < beds) {
            occupied++;
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (HostelRoom room : rooms) {
            if (room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }

    static HostelRoom safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);

        if (room != null) {
            room.allot(studentName);
            return room;
        }

        return null;
    }
}

class SrmStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name, String regNo,
               HostelFeeAccount feeAccount, HostelRoom room) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;

        totalStudents++;
    }

    String fullStatus() {
        String roomStatus;

        if (room != null)
            roomStatus = room.roomNo;
        else
            roomStatus = "unallotted";

        return name + " | Due: Rs " +
                feeAccount.getDue() +
                " | Room: " + roomStatus;
    }
}

public class FeeHostelManagement {
    public static void main(String[] args) {

        HostelFeeAccount fee1 =
                new HostelFeeAccount("101", 200000, 60000);

        HostelFeeAccount fee2 =
                new HostelFeeAccount("102", 200000, 20000);

        HostelFeeAccount fee3 =
                new HostelFeeAccount("103", 200000, 0);

        // Valid payment
        fee1.pay(0);

        // Rejected payment
        fee2.pay(-5000);

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1, 0),
            new HostelRoom("C-507", 1, 0)
        };

        HostelRoom room1 =
                HostelRoom.safeAllot(rooms, "Ravi");

        HostelRoom room2 =
                HostelRoom.safeAllot(rooms, "Anitha");

        HostelRoom room3 =
                HostelRoom.safeAllot(rooms, "Karthik");

        SrmStudent student1 =
                new SrmStudent("Ravi", "101", fee1, room1);

        SrmStudent student2 =
                new SrmStudent("Anitha", "102", fee2, room2);

        SrmStudent student3 =
                new SrmStudent("Karthik", "103", fee3, room3);

        System.out.println(student1.fullStatus());
        System.out.println(student2.fullStatus());
        System.out.println(student3.fullStatus());

        System.out.println("Total students: " +
                SrmStudent.totalStudents);
    }
}
