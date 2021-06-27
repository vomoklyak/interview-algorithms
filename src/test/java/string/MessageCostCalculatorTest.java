package string;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class MessageCostCalculatorTest {

    private MessageCostCalculator sut;

    @Test
    public void shouldCalculateMinCostCaseNullMessages() {
        // Given
        final List<String> messages = null;
        sut = new MessageCostCalculator(0.015D, 3, 0.01D, 7);
        
        // Whe
        final ThrowingCallable result = () -> sut.calculateMinCost(messages);

        // Then
        Assertions.assertThatThrownBy(result).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldCalculateMinCost() {
        // Given
        final List<String> messages = new LinkedList<>();
        messages.add("@@@");
        messages.add("aaaaaaa");
        sut = new MessageCostCalculator(0.015D, 3, 0.01D, 7);

        // When
        final double result = sut.calculateMinCost(messages);

        // Then
        Assertions.assertThat(result).isEqualTo(0.025D);
    }

    @Test
    public void shouldCalculateMinCostCaseFlushedGsm7MessageUcs2Message() {
        // Given
        final List<String> messages = new LinkedList<>();
        messages.add("aaaaaa@aa");
        sut = new MessageCostCalculator(0.015D, 3, 0.01D, 7);

        // When
        final double result = sut.calculateMinCost(messages);

        // Then
        Assertions.assertThat(result).isEqualTo(0.025);
    }

    @Test
    public void shouldCalculateMinCostCaseFlushedGsm7MessageUcs2MessageGsm7Message() {
        // Given
        final List<String> messages = new LinkedList<>();
        messages.add("aaaaaa@aaa");
        sut = new MessageCostCalculator(0.015D, 3, 0.01D, 7);

        // When
        final double result = sut.calculateMinCost(messages);

        // Then
        Assertions.assertThat(result).isEqualTo(0.035);
    }

    @Test
    public void shouldCalculateMinCostCaseUcs2MessageGsm7Message() {
        // Given
        final List<String> messages = new LinkedList<>();
        messages.add("aa@aaaaaaa");
        sut = new MessageCostCalculator(0.015D, 3, 0.01D, 7);

        // When
        final double result = sut.calculateMinCost(messages);

        // Then
        Assertions.assertThat(result).isEqualTo(0.025);
    }

    @Test
    public void shouldCalculateMinCostCaseGsm7Alphabet() {
        // Given
        final List<String> messages = new LinkedList<>();
        messages.add(" 0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz");
        sut = new MessageCostCalculator(0.015D, 3, 0.01D, 63);

        // When
        final double result = sut.calculateMinCost(messages);

        // Then
        Assertions.assertThat(result).isEqualTo(0.01);
    }

    @Test
    public void shouldCalculateMinCostCaseUcs2Alphabet() {
        // Given
        final List<String> messages = new LinkedList<>();
        messages.add("@ 0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz");
        sut = new MessageCostCalculator(0.015D, 64, 0.01D, 7);

        // When
        final double result = sut.calculateMinCost(messages);

        // Then
        Assertions.assertThat(result).isEqualTo(0.015);
    }

}