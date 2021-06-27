package array;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OccurrenceSearch {

    public static int searchInSortedArray(int[] sortedArray, int value) {
        if (sortedArray == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        int index = Arrays.binarySearch(sortedArray, value);
        if (index >= 0) {
            int numberOfOccurrences = 1;
            int leftIndex = index;
            while (--leftIndex >= 0 && sortedArray[leftIndex] == value) {
                numberOfOccurrences++;
            }
            int rightIndex = index;
            while (++rightIndex < sortedArray.length && sortedArray[rightIndex] == value) {
                numberOfOccurrences++;
            }
            return numberOfOccurrences;
        } else {
            return 0;
        }
    }

    public static int search(int[] array, int value) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        return (int) Arrays.stream(array)
                .filter(vl -> vl == value)
                .count();
    }

}