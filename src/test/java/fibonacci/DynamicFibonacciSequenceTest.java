package fibonacci;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import java.math.BigInteger;

public class DynamicFibonacciSequenceTest {

    @Test
    public void shouldGetElement() {
        // Given
        final int elementIndex = 50;

        // When
        final BigInteger result = DynamicFibonacciSequence.element(elementIndex);

        // Then
        Assertions.assertThat(result.longValue()).isEqualTo(20365011074L);
    }

    @Test
    public void shouldGetElementCaseNegativeIndex() {
        // Given
        final int elementIndex = -1;

        // When
        final ThrowingCallable result = () -> DynamicFibonacciSequence.element(elementIndex);

        // Then
        Assertions.assertThatThrownBy(result).isInstanceOf(IllegalArgumentException.class);
    }

}