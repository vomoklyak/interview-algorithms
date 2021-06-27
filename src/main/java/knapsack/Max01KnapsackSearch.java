package knapsack;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Max01KnapsackSearch {

    @ToString
    @RequiredArgsConstructor
    public static class Item {

        private final String name;
        private final int weight;
        private final int value;

    }

    public static int search(List<Item> items, int knapsackMaxWeight) {
        int numberOfRows = items.size() + 1;
        int numberOfColumns = knapsackMaxWeight + 1;
        int[][] valueMatrix = new int[numberOfColumns][numberOfRows];
        // row - itemIndex
        for (int currentItem = 1; currentItem < numberOfRows; currentItem++) {
            // column - weight
            for (int currentWeight = 1; currentWeight < numberOfColumns; currentWeight++) {
                Item item = items.get(currentItem - 1);
                if (item.weight > currentWeight) {
                    valueMatrix[currentWeight][currentItem] = valueMatrix[currentWeight][currentItem - 1];
                } else {
                    int valueWithoutCurrentItem = valueMatrix[currentWeight][currentItem - 1];
                    int valueWithCurrentItem = item.value + valueMatrix[currentWeight - item.weight][currentItem - 1];
                    valueMatrix[currentWeight][currentItem] = Math.max(valueWithoutCurrentItem, valueWithCurrentItem);
                }
            }
        }
        log.debug(matrixStr(valueMatrix, numberOfRows, numberOfColumns));
        return valueMatrix[numberOfColumns - 1][numberOfRows - 1];
    }

    private static String matrixStr(int[][] matrix, int numberOfRows, int numberOfColumns) {
        String lineSeparator = System.lineSeparator();
        StringBuilder matrixStringBuilder = new StringBuilder(lineSeparator);
        // column indices
        matrixStringBuilder.append("   ");
        IntStream.range(0, numberOfColumns).forEach(columnIndex ->
                matrixStringBuilder.append(String.format("%s  ", columnIndex)));
        matrixStringBuilder.append(lineSeparator);
        IntStream.range(0, numberOfRows).forEach(row -> {
                    // row indices
                    matrixStringBuilder.append(String.format("%s  ", row));
                    IntStream.range(0, numberOfColumns).forEach(column ->
                            matrixStringBuilder.append(String.format("%s, ", matrix[column][row])));
                    matrixStringBuilder.append(lineSeparator);
                }
        );
        return matrixStringBuilder.toString();
    }

}