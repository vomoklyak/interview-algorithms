package array;

import array.ClosestPointSearch.Point;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

public class ClosestPointSearchTest {

    @Test
    public void shouldSearchClosestPoints() {
        // Given
        final Point seedPoint = new Point(0D, 0D);
        final Point pointA = new Point(1D, 0D);
        final Point pointB = new Point(0D, -1D);
        final Point pointC = new Point(Math.sqrt(2) / 2, Math.sqrt(2) / 2);
        final Point[] points = {
                pointA,
                new Point(1D, 1D),
                pointB,
                new Point(-2D, 2D),
                pointC
        };
        final int numberOfPoints = 3;

        // When
        final Point[] result = ClosestPointSearch.search(seedPoint, points, numberOfPoints);

        // Then
        Assertions.assertThat(result).containsOnly(pointA, pointB, pointC);
    }

    @Test
    public void shouldSearchClosestPointsCaseEmptyPoints() {
        // Given
        final Point seedPoint = new Point(0D, 0D);
        final Point[] points = {};
        final int numberOfPoints = 3;

        // When
        final Point[] result = ClosestPointSearch.search(seedPoint, points, numberOfPoints);

        // Then
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void shouldSearchClosestPointsCaseZeroNumberOfPoints() {
        // Given
        final Point seedPoint = new Point(0D, 0D);
        final Point[] points = {
                new Point(1D, 1D),
                new Point(-2D, 2D),
        };
        final int numberOfPoints = 0;

        // When
        final Point[] result = ClosestPointSearch.search(seedPoint, points, numberOfPoints);

        // Then
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void shouldSearchClosestPointsCaseNullSeedPoint() {
        // Given
        final Point seedPoint = null;
        final Point[] points = {};
        final int numberOfPoints = 0;

        // When
        final ThrowingCallable result = () ->
                ClosestPointSearch.search(seedPoint, points, numberOfPoints);

        // Then
        Assertions.assertThatThrownBy(result).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldSearchClosestPointsCaseNullPoints() {
        // Given
        final Point seedPoint = new Point(0D, 0D);
        final Point[] points = null;
        final int numberOfPoints = 0;

        // When
        final ThrowingCallable result = () ->
                ClosestPointSearch.search(seedPoint, points, numberOfPoints);

        // Then
        Assertions.assertThatThrownBy(result).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldSearchClosestPointsCaseNegativeNumberOfPoints() {
        // Given
        final Point seedPoint = new Point(0D, 0D);
        final Point[] points = {};
        final int numberOfPoints = -1;

        // When
        final ThrowingCallable result = () ->
                ClosestPointSearch.search(seedPoint, points, numberOfPoints);

        // Then
        Assertions.assertThatThrownBy(result).isInstanceOf(IllegalArgumentException.class);
    }

}