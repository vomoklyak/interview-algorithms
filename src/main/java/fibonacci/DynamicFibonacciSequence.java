package fibonacci;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DynamicFibonacciSequence {

    public static BigInteger element(int elementIndex) {
        if (elementIndex < 0) {
            throw new IllegalArgumentException("Element index cannot be negative");
        }
        return generate(elementIndex);
    }

    private static BigInteger generate(int elementIndex) {
        BigInteger[] fibonacciSequence = new BigInteger[elementIndex + 2];
        fibonacciSequence[0] = BigInteger.ONE;
        log.trace("0 element {}", fibonacciSequence[0]);
        fibonacciSequence[1] = BigInteger.ONE;
        log.trace("1 element {}", fibonacciSequence[1]);
        for (int index = 2; index <= elementIndex; index++) {
            fibonacciSequence[index] = fibonacciSequence[index - 1].add(fibonacciSequence[index - 2]);
            log.trace("{} element {}", index, fibonacciSequence[index]);
        }
        return fibonacciSequence[elementIndex];
    }

}