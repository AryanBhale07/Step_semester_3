package main.java.constructors_keywords.class_problems;

class BusRoute {
    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 3);
    }

    int compareTo(BusRoute other) {

        if (priority != other.priority)
            return Integer.compare(other.priority, priority);

        int codeResult = routeCode.toLowerCase()
                .compareTo(other.routeCode.toLowerCase());

        if (codeResult != 0)
            return codeResult;

        return Integer.compare(routeName.length(),
                other.routeName.length());
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {

        BusRoute[] result = routes.clone();

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - 1 - i; j++) {

                if (result[j].compareTo(result[j + 1]) > 0) {
                    BusRoute temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    String getRouteCode() {
        return routeCode;
    }
}

public class BusRouteRanking {
    public static void main(String[] args) {

        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = BusRoute.rankRoutes(routes);

        for (BusRoute route : ranked) {
            System.out.println(route.getRouteCode());
        }
    }
}
