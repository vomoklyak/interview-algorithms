package array;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AlternativeSort {

    public static int[] sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        Arrays.sort(array);
        int resultIndex = 0;
        int length = array.length;
        int[] auxiliaryArray = array.clone();
        for (int index = 0; index < length / 2; index++) {
            array[resultIndex++] = auxiliaryArray[length - index - 1];
            array[resultIndex++] = auxiliaryArray[index];
        }
        if (length % 2 == 1) {
            array[resultIndex] = auxiliaryArray[length / 2];
        }
        return array;
    }

}