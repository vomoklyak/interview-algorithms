package knapsack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Max01KnapsackSearchTest {

    @Test
    public void shouldFindMaxValue() {
        // Given
        final int knapsackMaxWeight = 15;
        final List<Max01KnapsackSearch.Item> items = Arrays.asList(
                new Max01KnapsackSearch.Item("item-0", 4, 2),
                new Max01KnapsackSearch.Item("item-1", 4, 4),
                new Max01KnapsackSearch.Item("item-2", 6, 4),
                new Max01KnapsackSearch.Item("item-3", 8, 5)
        );

        // When
        final int result = Max01KnapsackSearch.search(items, knapsackMaxWeight);

        // Than
        Assertions.assertThat(result).isEqualTo(10);
    }

}