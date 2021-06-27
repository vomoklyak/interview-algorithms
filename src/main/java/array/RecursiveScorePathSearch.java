package array;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RecursiveScorePathSearch {

    public static List<Path> search(int score, List<Integer> points) {
        List<Path> paths = new ArrayList<>();
        search(score, points, new Path(), paths);
        return paths.stream()
                .peek(Path::normalize)
                .distinct()
                .collect(Collectors.toList());
    }

    private static void search(int score, List<Integer> points, Path path, List<Path> paths) {
        points.forEach(point -> {
            if (point < score) {
                search(score - point, points, path.addPoint(point), paths);
            } else if (point == score) {
                paths.add(path.addPoint(point));
            }
        });
    }

    @EqualsAndHashCode
    public static class Path {

        @Getter
        private final List<Integer> points = new ArrayList<>();

        @Override
        public String toString() {
            return String.format("Path %s", points);
        }

        private void normalize() {
            Collections.sort(points);
        }

        private Path addPoint(int point) {
            Path subPath = new Path();
            subPath.points.addAll(this.points);
            subPath.points.add(point);
            return subPath;
        }

    }

}