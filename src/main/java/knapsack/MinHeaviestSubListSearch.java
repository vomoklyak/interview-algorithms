package knapsack;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MinHeaviestSubListSearch {

    // O(N*N), N - weights size
    public static List<Integer> search(List<Integer> weights) {
        Map<Integer, Integer> weightToAggWeight = weights.stream()
                .collect(Collectors.toMap(Function.identity(), Function.identity(), Integer::sum));
        log.trace("Weight to aggregated weight: {}", weightToAggWeight);
        List<Entry<Integer, Integer>> items = new ArrayList<>(weightToAggWeight.entrySet());
        // 01knapsack
        int numberOfRows = weightToAggWeight.size() + 1;
        int numberOfColumns = weights.size() + 1;
        Cell[][] valueMatrix = valueMatrix(numberOfRows, numberOfColumns);
        // row - itemIndex
        for (int currentItem = 1; currentItem < numberOfRows; currentItem++) {
            // column - quantity
            for (int currentQuantity = 1; currentQuantity < numberOfColumns; currentQuantity++) {
                Entry<Integer, Integer> item = items.get(currentItem - 1);
                int itemValue = item.getValue();
                int itemQuantity = item.getValue() / item.getKey();
                if (itemQuantity > currentQuantity) {
                    valueMatrix[currentQuantity][currentItem] = valueMatrix[currentQuantity][currentItem - 1];
                } else {
                    Cell cellWithoutCurrentItem = valueMatrix[currentQuantity][currentItem - 1];
                    Cell cellWithCurrentItem = valueMatrix[currentQuantity - itemQuantity][currentItem - 1]
                            .add(itemValue, item.getKey());
                    valueMatrix[currentQuantity][currentItem] = cellWithCurrentItem.compareTo(
                            cellWithoutCurrentItem) < 0 ? cellWithoutCurrentItem : cellWithCurrentItem;
                }
            }
        }
        int totalWeight = valueMatrix[numberOfColumns - 1][numberOfRows - 1].value;
        List<Integer> minHeaviestWeights = valueMatrix[0][numberOfRows - 1].weights;
        for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
            if (2 * valueMatrix[columnIndex][numberOfRows - 1].value > totalWeight) {
                minHeaviestWeights = valueMatrix[columnIndex][numberOfRows - 1].weights;
                break;
            }
        }
        minHeaviestWeights.sort(Comparator.naturalOrder());
        List<Integer> minHeaviestSubList = new LinkedList<>();
        for (int weight : minHeaviestWeights) {
            for (int index = 0; index < weightToAggWeight.get(weight) / weight; index++) {
                minHeaviestSubList.add(weight);
            }
        }
        log.trace("Min heaviest sub list: {}", minHeaviestSubList);
        return minHeaviestSubList;
    }

    private static Cell[][] valueMatrix(int numberOfRows, int numberOfColumns) {
        Cell[][] valueMatrix = new Cell[numberOfColumns][numberOfRows];
        for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
            valueMatrix[columnIndex][0] = new Cell(0, new ArrayList<>());
        }
        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            valueMatrix[0][rowIndex] = new Cell(0, new ArrayList<>());
        }
        return valueMatrix;
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Cell implements Comparable<Cell> {

        private final int value;
        private final List<Integer> weights;

        @Override
        public int compareTo(Cell that) {
            return Integer.compare(this.value, that.value);
        }

        private Cell add(int value, int itemIndex) {
            List<Integer> itemIndexes = new ArrayList<>(this.weights);
            itemIndexes.add(itemIndex);
            return new Cell(this.value + value, itemIndexes);
        }

    }

}