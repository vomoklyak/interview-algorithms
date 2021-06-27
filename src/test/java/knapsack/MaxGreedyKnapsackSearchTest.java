package knapsack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MaxGreedyKnapsackSearchTest {

    @Test
    public void shouldSearchMaxValueKnapsack() {
        // Given
        final double knapsackMaxWeight = 2.5;
        final List<MaxGreedyKnapsackSearch.DividableItem> items = Arrays.asList(
                new MaxGreedyKnapsackSearch.DividableItem("item-1", 2.5D, 25),
                new MaxGreedyKnapsackSearch.DividableItem("item-2", 1.5D, 60),
                new MaxGreedyKnapsackSearch.DividableItem("item-3", 0.5D, 35)
        );

        // When
        final double result = MaxGreedyKnapsackSearch.search(items, knapsackMaxWeight);

        // Then
        Assertions.assertThat(result).isEqualTo(100D);
    }

    @Test
    public void shouldSearchMaxValueKnapsackCaseInfiniteValueCapacity() {
        // Given
        final double knapsackMaxWeight = 2.5;
        final List<MaxGreedyKnapsackSearch.DividableItem> items = Arrays.asList(
                new MaxGreedyKnapsackSearch.DividableItem("item-1", 2.5D, 25),
                new MaxGreedyKnapsackSearch.DividableItem("item-2", 0.0D, 2000)
        );

        // When
        final double result = MaxGreedyKnapsackSearch.search(items, knapsackMaxWeight);

        // Then
        Assertions.assertThat(result).isEqualTo(2025D);
    }

}