package main.java.access_modifiers.class_problems;

import java.util.Arrays;

class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    static int totalSummaries;

    static {
        totalSummaries = 0;
    }

    public DischargeSummary(String patientId,
                            String[] medicationCodes) {

        if (patientId == null || patientId.trim().isEmpty())
            throw new IllegalArgumentException("Invalid patient ID");

        if (medicationCodes == null)
            throw new IllegalArgumentException("Invalid medication list");

        for (String code : medicationCodes) {
            if (code == null ||
                !code.matches("MED-[A-Z]")) {

                throw new IllegalArgumentException(
                        "Invalid medication code");
            }
        }

        this.patientId = patientId;
        this.medicationCodes =
                Arrays.copyOf(
                    medicationCodes,
                    medicationCodes.length);

        totalSummaries++;
    }

    public String[] getMedicationCodes() {
        return Arrays.copyOf(
                medicationCodes,
                medicationCodes.length);
    }

    public DischargeSummary withCorrectedMedication(
            int index, String newCode) {

        if (index < 0 ||
            index >= medicationCodes.length)
            throw new IllegalArgumentException("Invalid index");

        if (newCode == null ||
            !newCode.matches("MED-[A-Z]"))
            throw new IllegalArgumentException(
                    "Invalid medication code");

        String[] copy = getMedicationCodes();
        copy[index] = newCode;

        return new DischargeSummary(
                patientId, copy);
    }
}

class CriticalCareDischargeSummary
        extends DischargeSummary {

    private int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
}

public class DischargeSummary1 {

    static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int critical = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {

            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof CriticalCareDischargeSummary)
                critical++;
            else
                routine++;
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                critical + " critical-care | " +
                routine + " routine";
    }

    public static void main(String[] args) {

        DischargeSummary[] summaries = {
            new CriticalCareDischargeSummary(
                "MT001",
                new String[]{"MED-X"},
                4),

            null,

            new DischargeSummary(
                "MT002",
                new String[]{"MED-Y"})
        };

        System.out.println(
                processNightlyBatch(summaries));
    }
}