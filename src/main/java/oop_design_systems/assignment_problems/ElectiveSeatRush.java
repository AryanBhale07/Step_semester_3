package main.java.oop_design_systems.assignment_problems;

import java.util.*;

interface CreditPolicy {
    int getCreditLimit();
}

class RegularPolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 24;
    }
}

class HonorsPolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 28;
    }
}

class ExchangePolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 20;
    }
}

class Student {
    String name;
    int currentCredits;
    CreditPolicy policy;

    public Student(String name, int currentCredits,
                   CreditPolicy policy) {
        this.name = name;
        this.currentCredits = currentCredits;
        this.policy = policy;
    }

    public boolean canTake(int credits) {
        return currentCredits + credits <=
                policy.getCreditLimit();
    }

    public void addCredits(int credits) {
        currentCredits += credits;
    }

    public void removeCredits(int credits) {
        currentCredits -= credits;
    }
}

class Elective {
    private String name;
    private int credits;
    private int capacity;

    private List<Student> enrolled =
            new ArrayList<>();

    private Queue<Student> waitlist =
            new LinkedList<>();

    public Elective(String name, int credits,
                    int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public void enroll(Student student) {
        if (!student.canTake(credits)) {
            System.out.println(
                "Enrollment failed: " +
                student.name +
                " would exceed the credit limit."
            );
            return;
        }

        if (enrolled.contains(student) ||
            waitlist.contains(student)) {
            System.out.println(
                student.name +
                " is already registered."
            );
            return;
        }

        if (enrolled.size() >= capacity) {
            waitlist.add(student);

            System.out.println(
                name + " is full."
            );

            System.out.println(
                student.name +
                " added to waitlist."
            );

            return;
        }

        enrolled.add(student);
        student.addCredits(credits);

        System.out.println(
            student.name +
            " enrolled in " + name +
            " (credits: " +
            student.currentCredits +
            "/" +
            student.policy.getCreditLimit() +
            ")."
        );
    }

    public void drop(Student student) {
        if (!enrolled.remove(student))
            return;

        student.removeCredits(credits);

        System.out.println(
            student.name +
            " dropped " + name +
            " (credits: " +
            student.currentCredits +
            "/" +
            student.policy.getCreditLimit() +
            ")."
        );

        promote();
    }

    private void promote() {
        Iterator<Student> iterator =
                waitlist.iterator();

        while (iterator.hasNext() &&
               enrolled.size() < capacity) {

            Student student = iterator.next();

            if (student.canTake(credits)) {
                iterator.remove();
                enrolled.add(student);
                student.addCredits(credits);

                System.out.println(
                    student.name +
                    " promoted from waitlist and enrolled in " +
                    name +
                    " (credits: " +
                    student.currentCredits +
                    "/" +
                    student.policy.getCreditLimit() +
                    ")."
                );

                break;
            }
        }
    }
}

public class ElectiveSeatRush {
    public static void main(String[] args) {
        Elective elective =
                new Elective("Cloud Computing", 4, 2);

        Student asha =
                new Student("Asha", 20,
                        new RegularPolicy());

        Student ravi =
                new Student("Ravi", 22,
                        new HonorsPolicy());

        Student neha =
                new Student("Neha", 12,
                        new ExchangePolicy());

        Student kiran =
                new Student("Kiran", 22,
                        new RegularPolicy());

        elective.enroll(asha);
        elective.enroll(ravi);
        elective.enroll(neha);
        elective.enroll(kiran);

        elective.drop(asha);
    }
}