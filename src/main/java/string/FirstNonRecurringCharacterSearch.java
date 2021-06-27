package string;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FirstNonRecurringCharacterSearch {

    public static Optional<Character> search(String string, int alphabetSize) {
        if (string == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        if (string.length() == Integer.MAX_VALUE) {
            throw new IllegalArgumentException(String.format(
                    "String size should be less then %d", Integer.MAX_VALUE));
        }
        int firstNonRecurringSymbolIndex = Integer.MAX_VALUE;
        for (CharacterStat characterStat : characterStats(string, alphabetSize)) {
            if (characterStat != null && !characterStat.recurring()) {
                firstNonRecurringSymbolIndex = Math.min(
                        characterStat.lastOccurrenceIndex, firstNonRecurringSymbolIndex);
            }
        }
        return firstNonRecurringSymbolIndex == Integer.MAX_VALUE ?
                Optional.empty() : Optional.of(string.charAt(firstNonRecurringSymbolIndex));
    }

    private static CharacterStat[] characterStats(String string, int alphabetSize) {
        CharacterStat[] characterStats = new CharacterStat[alphabetSize];
        for (int index = 0; index < string.length(); index++) {
            if (characterStats[string.charAt(index)] == null) {
                characterStats[string.charAt(index)] = new CharacterStat();
            }
            characterStats[string.charAt(index)].numberOfOccurrences++;
            characterStats[string.charAt(index)].lastOccurrenceIndex = index;
        }
        return characterStats;
    }

    private static class CharacterStat {

        private int numberOfOccurrences = 0;
        private int lastOccurrenceIndex = -1;

        private boolean recurring() {
            return numberOfOccurrences > 1;
        }

    }

}