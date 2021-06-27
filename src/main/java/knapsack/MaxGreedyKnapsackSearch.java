package knapsack;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MaxGreedyKnapsackSearch {

    @Getter
    @ToString
    public static class DividableItem implements Comparable<DividableItem> {

        private final String name;
        private final double weight;
        private final double value;
        private final double valueCapacity;

        public DividableItem(String name, double weight, double value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
            this.valueCapacity = weight == 0 ? Double.POSITIVE_INFINITY : value / weight;
        }

        @Override
        public int compareTo(DividableItem that) {
            return Double.compare(this.valueCapacity, that.valueCapacity);
        }

        public double value(double weight) {
            return weight == 0 ? value : weight * valueCapacity;
        }

    }

    public static double search(List<DividableItem> items, double knapsackMaxWeight) {
        items.sort(Comparator.reverseOrder());
        double knapsackValue = 0.0;
        double knapsackWeight = 0.0;
        for (int itemIndex = 0; itemIndex < items.size() && knapsackWeight < knapsackMaxWeight; itemIndex++) {
            DividableItem item = items.get(itemIndex);
            double weight = Math.min(item.weight, knapsackMaxWeight - knapsackWeight);
            log.trace("Add item {} weight {} value {}", item.name, weight, item.value(weight));
            knapsackWeight += weight;
            knapsackValue += item.value(weight);
        }
        return knapsackValue;
    }

}