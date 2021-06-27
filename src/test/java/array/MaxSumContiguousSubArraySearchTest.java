package array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MaxSumContiguousSubArraySearchTest {

    @Test
    public void shouldFindMaxSum() {
        // Given
        final int[] array = {-2, 3, -4, 1, 4, 5, -2, -5, 2, 1};

        // When
        final int result = MaxSumContiguousSubArraySearch.search(array);

        // Then
        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    public void shouldFindMaxSumCaseNegativeNumbers() {
        // Given
        final int[] array = {-5, -1, -10, -12};

        // When
        final int result = MaxSumContiguousSubArraySearch.search(array);

        // Then
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    public void shouldFindMaxSumCaseEmptyArray() {
        // Given
        final int[] array = {};

        // When
        final int result = MaxSumContiguousSubArraySearch.search(array);

        // Then
        Assertions.assertThat(result).isEqualTo(Integer.MIN_VALUE);
    }

}