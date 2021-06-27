package fibonacci;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

public class FibonacciSequenceTest {

    @Test
    public void shouldGetElement() {
        // Given
        final int elementIndex = 11;

        // When
        final long result = FibonacciSequence.element(elementIndex);

        // Then
        Assertions.assertThat(result).isEqualTo(144);
    }

    @Test
    public void shouldGetElementCaseNegativeIndex() {
        // Given
        final int elementIndex = -1;

        // When
        final ThrowingCallable result = () -> FibonacciSequence.element(elementIndex);

        // Then
        Assertions.assertThatThrownBy(result).isInstanceOf(IllegalArgumentException.class);
    }

}