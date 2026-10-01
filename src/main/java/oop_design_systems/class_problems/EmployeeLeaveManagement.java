package main.java.oop_design_systems.class_problems;

import java.time.LocalDate;

abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public abstract boolean isLeaveAllowed(
            LocalDate start, LocalDate end);
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(
            LocalDate start, LocalDate end) {
        return true;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(
            LocalDate start, LocalDate end) {
        return true;
    }
}

enum LeaveStatus {
    PENDING, APPROVED, REJECTED
}

class LeaveRequest {
    private Employee employee;
    LocalDate start;
    LocalDate end;
    private LeaveStatus status = LeaveStatus.PENDING;

    public LeaveRequest(Employee employee,
                        LocalDate start,
                        LocalDate end) {
        this.employee = employee;
        this.start = start;
        this.end = end;
    }

    public void approve() {
        if (status != LeaveStatus.PENDING) {
            System.out.println(
                "Cannot change status: Approved request cannot revert to Pending."
            );
            return;
        }

        status = LeaveStatus.APPROVED;

        System.out.println(
            "Leave request for " + employee.name +
            " approved. Status: Approved."
        );
    }

    public void reject() {
        if (status != LeaveStatus.PENDING)
            return;

        status = LeaveStatus.REJECTED;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public Employee getEmployee() {
        return employee;
    }
}

class LeaveManager {
    public LeaveRequest submit(Employee employee,
                               LocalDate start,
                               LocalDate end) {

        if (!employee.isLeaveAllowed(start, end)) {
            System.out.println("Leave request rejected.");
            return null;
        }

        LeaveRequest request =
                new LeaveRequest(employee, start, end);

        System.out.println(
            "Leave request submitted by " +
            employee.name + " for " +
            start + " to " + end +
            ". Status: Pending."
        );

        return request;
    }
}

public class EmployeeLeaveManagement {
    public static void main(String[] args) {
        LeaveManager manager = new LeaveManager();

        Employee john =
                new FullTimeEmployee("John Doe");

        Employee jane =
                new PartTimeEmployee("Jane Smith");

        LeaveRequest r1 = manager.submit(
                john,
                LocalDate.of(2024, 10, 10),
                LocalDate.of(2024, 10, 12)
        );

        r1.approve();

        manager.submit(
                jane,
                LocalDate.of(2024, 11, 1),
                LocalDate.of(2024, 11, 5)
        );

        r1.approve();
    }
}
