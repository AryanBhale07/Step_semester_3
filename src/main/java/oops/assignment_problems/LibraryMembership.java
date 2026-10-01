package main.java.oops.assignment_problems;

class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

class LibraryMember {
    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "SRM Library";
    static int memberCount = 1000;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        this.memberId = "LM-" + memberCount;
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + (memberCount - 1000));
    }
}

public class LibraryMembership {
    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
                new BrokenLibraryMember("Aditi", "LM-1001", 2);

        BrokenLibraryMember member2 =
                new BrokenLibraryMember("Rohan", "LM-1002", 3);

        System.out.println(member1.name);
        System.out.println(member2.name);

        // Static fields are shared by all objects, so the second
        // object overwrites the first object's data.

        System.out.println();

        System.out.println("Fixed version:");

        LibraryMember fixed1 =
                new LibraryMember("Aditi", 2);

        LibraryMember fixed2 =
                new LibraryMember("Rohan", 3);

        fixed1.printMemberCard();
        fixed2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}