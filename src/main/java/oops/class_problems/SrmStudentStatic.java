package main.java.oops.class_problems;

class BrokenSrmStudent {
    static String name;
    static String regNo;
    static int attendance;

    BrokenSrmStudent(String name, String regNo, int attendance) {
        BrokenSrmStudent.name = name;
        BrokenSrmStudent.regNo = regNo;
        BrokenSrmStudent.attendance = attendance;
    }
}

class SrmStudentFixed {
    String name;
    String regNo;
    int attendance;

    static String university = "SRM";
    static int admissionCount = 0;

    SrmStudentFixed(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;

        admissionCount++;
        this.regNo = "RA2311003010" + admissionCount;
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }
}

public class SrmStudentStatic {
    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenSrmStudent student1 =
                new BrokenSrmStudent("Ravi", "RA001", 82);

        BrokenSrmStudent student2 =
                new BrokenSrmStudent("Meera", "RA002", 91);

        System.out.println(student1.name);
        System.out.println(student2.name);

        // name, regNo and attendance are student-specific,
        // so making them static causes all objects to share them.

        System.out.println();

        System.out.println("Fixed version:");

        SrmStudentFixed fixed1 =
                new SrmStudentFixed("Ravi", 82);

        SrmStudentFixed fixed2 =
                new SrmStudentFixed("Meera", 91);

        fixed1.printIdCard();
        fixed2.printIdCard();

        SrmStudentFixed.printTotalAdmissions();
    }
}
