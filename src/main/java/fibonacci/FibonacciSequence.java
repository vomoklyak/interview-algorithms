package fibonacci;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FibonacciSequence {

    public static long element(int elementIndex) {
        if (elementIndex < 0) {
            throw new IllegalArgumentException("Element index cannot be negative");
        }
        return generate(elementIndex);
    }

    private static long generate(int elementIndex) {
        if (elementIndex <= 1) {
            log.trace("{} element 1", elementIndex);
            return 1;
        }
        long element = generate(elementIndex - 1) + generate(elementIndex - 2);
        log.trace("{} element {}", elementIndex, element);
        return element;
    }

}