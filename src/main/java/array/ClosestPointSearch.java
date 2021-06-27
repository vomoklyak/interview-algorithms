package array;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.PriorityQueue;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClosestPointSearch {

    // n*ln(k), n - number of points, k - number of closest points
    public static Point[] search(Point seedPoint, Point[] points, int numberOfClosestPoints) {
        valid(seedPoint, points, numberOfClosestPoints);
        PriorityQueue<Distance> closestDistances =
                new PriorityQueue<>(Comparator.comparing(Distance::getDistance).reversed());
        for (Point point : points) {
            closestDistances.offer(seedPoint.distance(point));
            if (closestDistances.size() > numberOfClosestPoints) {
                closestDistances.poll();
            }
        }
        return closestDistances.stream()
                .map(Distance::getTo)
                .toArray(Point[]::new);
    }

    private static void valid(Point seedPoint, Point[] points, int numberOfClosestPoints) {
        if (seedPoint == null) {
            throw new IllegalArgumentException("Parameter seedPoint cannot be null");
        }
        if (points == null) {
            throw new IllegalArgumentException("Parameter points cannot be null");
        }
        if (numberOfClosestPoints < 0) {
            throw new IllegalArgumentException("Parameter numberOfClosestPoints cannot be negative");
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Point {

        private final double ordinateX;
        private final double ordinateY;

        public Distance distance(Point point) {
            double distXPow2 = Math.pow((ordinateX - point.ordinateX), 2);
            double distYPow2 = Math.pow((ordinateY - point.ordinateY), 2);
            return new Distance(this, point, Math.sqrt(distXPow2 + distYPow2));
        }

    }

    @Getter
    @RequiredArgsConstructor
    public static class Distance {

        private final Point from;
        private final Point to;
        private final double distance;

    }

}