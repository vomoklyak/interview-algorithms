package array;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MinimumSwapSearch {

    public static int search(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        int numberOfSwaps = 0;
        for (int index = 0; index < array.length; index++) {
            while (array[index] != index) {
                int value = array[array[index]];
                array[array[index]] = array[index];
                array[index] = value;
                numberOfSwaps++;
            }
        }
        return numberOfSwaps;
    }

}