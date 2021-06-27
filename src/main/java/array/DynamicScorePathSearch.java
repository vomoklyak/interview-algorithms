package array;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DynamicScorePathSearch {

    public static int search(int score, List<Integer> points) {
        int numberOfScores = score + 1;
        int[] scores = IntStream.range(0, numberOfScores)
                .map(currentScore -> currentScore % points.get(0) == 0 ? 1 : 0).toArray();
        for (int pointIndex = 1; pointIndex < points.size(); pointIndex++) {
            int point = points.get(pointIndex);
            int[] currentScores = scores.clone();
            for (int currentScore = 1; currentScore < numberOfScores; currentScore++) {
                for (int pointScore = point; pointScore <= currentScore; pointScore += point) {
                    currentScores[currentScore] += currentScore == pointScore ? 1 : scores[currentScore - pointScore];
                }
            }
            scores = currentScores;
        }
        return scores[numberOfScores - 1];
    }

}