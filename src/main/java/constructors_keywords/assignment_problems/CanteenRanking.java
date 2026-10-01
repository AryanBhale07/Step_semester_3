package main.java.constructors_keywords.assignment_problems;

class Canteen {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    int compareTo(Canteen other) {

        if (trustScore != other.trustScore)
            return Integer.compare(other.trustScore, trustScore);

        int codeResult = canteenCode.toLowerCase()
                .compareTo(other.canteenCode.toLowerCase());

        if (codeResult != 0)
            return codeResult;

        return Integer.compare(
                canteenName.length(),
                other.canteenName.length());
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {

        Canteen[] result = canteens.clone();

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - 1 - i; j++) {

                if (result[j].compareTo(result[j + 1]) > 0) {

                    Canteen temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    String getCode() {
        return canteenCode;
    }
}

public class CanteenRanking {
    public static void main(String[] args) {

        Canteen[] canteens = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked =
                Canteen.rankCanteens(canteens);

        for (Canteen canteen : ranked) {
            System.out.println(canteen.getCode());
        }
    }
}
