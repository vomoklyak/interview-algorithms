package array;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ZeroSumContiguousSubArraySearch {

    // 2*n
    public static int[] search(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        int sum = 0;
        Set<Integer> sums = new HashSet<>();
        for (int index = 0; index < array.length; index++) {
            int value = array[index];
            sum += value;
            if (value == 0) {
                return new int[]{value};
            } else if (sum == 0) {
                return Arrays.copyOf(array, index + 1);
            } else if (!sums.add(sum)) {
                int subSum = 0;
                int subIndex = 0;
                while (subIndex < index && subSum != sum) {
                    subSum += array[subIndex];
                }
                return Arrays.copyOfRange(array, subIndex + 1, index + 1);
            }
        }
        return new int[]{};
    }

}