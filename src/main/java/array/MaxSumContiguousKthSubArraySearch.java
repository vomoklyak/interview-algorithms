package array;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MaxSumContiguousKthSubArraySearch {

    public static int search(int[] array, int subArrayLength) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (array.length < subArrayLength) {
            throw new IllegalArgumentException("Array length should be greater than sub array length");
        }
        int sum = Arrays.stream(array, 0, subArrayLength).sum();
        int maxSum = sum;
        for (int index = 1; index < array.length - subArrayLength + 1; index++) {
            sum = sum - array[index - 1] + array[index + subArrayLength - 1];
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

}
