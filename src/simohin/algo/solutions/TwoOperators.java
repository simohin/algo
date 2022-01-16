package simohin.algo.solutions;

import java.util.*;

/**
 * Find a winner between two cellular providers
 * <p>
 * input:
 * first row - two integers p and q, p - antenna streaming radius, q - city radius
 * next 8 rows - first provider's antennas coordinates
 * next 8 rows - second provider's antennas coordinates
 * in "x y" format
 * <p>
 * only antennas which covers at list one point of the city takes part in competition, so, firstly filter other
 * in results need to decide who is the winner 1 or 2 - who have closer antenna to center
 * and count a prestige - number of antennas which closer to city center, then closest other provider's antenna
 * <p>
 * output:
 * first row - number of winner (or 0 if nobody wins)
 * second row - prestige (0 if nobody wins)
 */
class TwoOperators {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int COUNT = 8;
    private static final String PARAM_DELIMITER = " ";

    public static void main(String[] args) {

        int winner = 0, prestige = 0;

        var pq = readNextLine();
        double p = pq.x;
        double q = pq.y;

        var first = new LinkedHashSet<Point>();
        var second = new LinkedHashSet<Point>();

        for (int i = 0; i < COUNT; i++) {
            var point = readNextLine();
            if (coversCenter(p, q, point)) first.add(point);
        }

        for (int i = 0; i < COUNT; i++) {
            var point = readNextLine();
            if (coversCenter(p, q, point)) second.add(point);
        }

        var closestFirst = getClosestToCenter(first);
        var closestSecond = getClosestToCenter(second);

        if (closestFirst.isEmpty() && closestSecond.isEmpty()) {
            printResult(winner, prestige);
            return;
        }

        if (closestFirst.isEmpty()) {
            winner = 2;
            prestige = second.size();
            printResult(winner, prestige);
            return;
        }

        if (closestSecond.isEmpty()) {
            winner = 1;
            prestige = first.size();
            printResult(winner, prestige);
            return;
        }
        if (closestFirst.get().getDistance() == closestSecond.get().getDistance()) {
            printResult(winner, prestige);
            return;
        }
        if (closestFirst.get().getDistance() < closestSecond.get().getDistance()) {
            winner = 1;
            prestige = getPrestige(closestSecond.get(), first);
        } else {
            winner = 2;
            prestige = getPrestige(closestFirst.get(), second);
        }

        printResult(winner, prestige);
    }

    private static int getPrestige(Point point, Set<Point> other) {
        int prestige = 0;
        var distance = point.getDistance();

        for (var o : other) {
            var otherDistance = o.getDistance();
            if (distance > otherDistance) prestige++;
        }
        return prestige;
    }

    private static boolean coversCenter(double p, double q, Point point) {
        return point.getDistance() <= p + q;
    }

    private static void printResult(int winner, int prestige) {
        System.out.println(winner);
        System.out.println(prestige);
    }

    private static Optional<Point> getClosestToCenter(HashSet<Point> first) {
        return first.stream().min(Comparator.comparingDouble(Point::getDistance));
    }

    private static Point readNextLine() {
        var kv = SCANNER.nextLine().split(PARAM_DELIMITER);
        return new Point(Integer.parseInt(kv[0]), Integer.parseInt(kv[1]));
    }

    private static class Point {

        private final int x;
        private final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getDistance() {
            return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }
    }
}