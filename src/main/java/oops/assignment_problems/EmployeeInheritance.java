package main.java.oops.assignment_problems;

class Employee {
    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(int empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(int empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        if (getSalary() < stipendCap)
            return getSalary();

        return stipendCap;
    }
}

public class EmployeeInheritance {
    public static void main(String[] args) {
        Employee plain = new Employee(101, "Ravi", 40000);

        Employee manager =
                new ManagerEmployee(102, "Karan", 70000, 8000);

        Employee intern =
                new InternEmployee(103, "Meera", 12000, 10000);

        if (plain instanceof ManagerEmployee) {
            System.out.println("Manager effective pay: Rs " +
                    ((ManagerEmployee) plain).effectiveSalary());
        } else if (plain instanceof InternEmployee) {
            System.out.println("Intern effective pay: Rs " +
                    ((InternEmployee) plain).effectiveSalary());
        } else {
            System.out.println("Plain employee pay: Rs " +
                    plain.getSalary());
        }

        if (manager instanceof ManagerEmployee) {
            System.out.println("Manager effective pay: Rs " +
                    ((ManagerEmployee) manager).effectiveSalary());
        }

        if (intern instanceof InternEmployee) {
            System.out.println("Intern effective pay: Rs " +
                    ((InternEmployee) intern).effectiveSalary());
        }
    }
}
