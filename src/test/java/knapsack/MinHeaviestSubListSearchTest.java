package knapsack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MinHeaviestSubListSearchTest {

    @Test
    public void shouldSearchMinHeaviestSubArray() {
        // Given
        final List<Integer> weights = Arrays.asList(3, 7, 5, 6, 2);

        // When
        final List<Integer> result = MinHeaviestSubListSearch.search(weights);

        // Then
        Assertions.assertThat(result).containsExactly(6, 7);
    }

    @Test
    public void shouldSearchMinHeaviestSubArrayCaseAggregatedWeight() {
        // Given
        final List<Integer> weights = Arrays.asList(4, 7, 4);

        // When
        final List<Integer> result = MinHeaviestSubListSearch.search(weights);

        // Then
        Assertions.assertThat(result).containsExactly(4, 4);
    }

    @Test
    public void shouldSearchMinHeaviestSubArrayCaseEqualAggregatedWeight() {
        // Given
        final List<Integer> weights = Arrays.asList(4, 3, 7, 4, 4, 3, 2, 1);

        // When
        final List<Integer> result = MinHeaviestSubListSearch.search(weights);

        // Then
        Assertions.assertThat(result).containsExactly(4, 4, 4, 7);
    }

    @Test
    public void shouldSearchMinHeaviestSubArrayCaseEqualWeights() {
        // Given
        final List<Integer> weights = Arrays.asList(1, 1, 1);

        // When
        final List<Integer> result = MinHeaviestSubListSearch.search(weights);

        // Then
        Assertions.assertThat(result).containsExactly(1, 1, 1);
    }

    @Test
    public void shouldSearchMinHeaviestSubArrayCaseMinNumberOfElements() {
        // Given
        final List<Integer> weights = Arrays.asList(4, 2, 2, 1, 2, 2, 4);

        // When
        final List<Integer> result = MinHeaviestSubListSearch.search(weights);

        // Then
        Assertions.assertThat(result).containsExactly(1, 4, 4);
    }

}