package main.java.access_modifiers.assignment_problems;

public class MembershipFieldReachChecker {

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        if (fieldModifier.equals("private"))
            return accessorContext.equals("SAME_CLASS")
                    ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("default"))
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("protected"))
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("public"))
            return "ALLOWED";

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        int privateAllowed = 0;
        int privateDenied = 0;

        int defaultAllowed = 0;
        int defaultDenied = 0;

        int protectedAllowed = 0;
        int protectedDenied = 0;

        int publicAllowed = 0;
        int publicDenied = 0;

        for (String[] attempt : attempts) {

            String modifier = attempt[0];

            boolean allowed =
                    classifyAccess(
                        attempt[0], attempt[1])
                    .equals("ALLOWED");

            if (modifier.equals("private")) {
                if (allowed) privateAllowed++;
                else privateDenied++;
            } else if (modifier.equals("default")) {
                if (allowed) defaultAllowed++;
                else defaultDenied++;
            } else if (modifier.equals("protected")) {
                if (allowed) protectedAllowed++;
                else protectedDenied++;
            } else if (modifier.equals("public")) {
                if (allowed) publicAllowed++;
                else publicDenied++;
            }
        }

        return "private: " + privateAllowed +
                " allowed / " + privateDenied + " denied | " +
                "default: " + defaultAllowed +
                " allowed / " + defaultDenied + " denied | " +
                "protected: " + protectedAllowed +
                " allowed / " + protectedDenied + " denied | " +
                "public: " + publicAllowed +
                " allowed / " + publicDenied + " denied";
    }

    public static void main(String[] args) {

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                summarizeByModifier(attempts));
    }
}