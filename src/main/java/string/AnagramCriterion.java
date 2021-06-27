package string;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public final class AnagramCriterion {

    public static boolean anagram(String left, String right, int alphabetSize) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Strings cannot be null");
        }
        if (left.length() == right.length()) {
            return Arrays.stream(characterOccurrences(left, right, alphabetSize))
                    .filter(Objects::nonNull)
                    .allMatch(CharacterOccurrence::equalOccurrences);
        }
        return false;
    }

    private static CharacterOccurrence[] characterOccurrences(String left, String right, int alphabetSize) {
        int length = Math.min(left.length(), right.length());
        CharacterOccurrence[] characterOccurrences = new CharacterOccurrence[alphabetSize];
        for (int index = 0; index < length; index++) {
            if (characterOccurrences[left.charAt(index)] == null) {
                characterOccurrences[left.charAt(index)] = new CharacterOccurrence();
            }
            characterOccurrences[left.charAt(index)].numberOfLeftOccurrences++;

            if (characterOccurrences[right.charAt(index)] == null) {
                characterOccurrences[right.charAt(index)] = new CharacterOccurrence();
            }
            characterOccurrences[right.charAt(index)].numberOfRightOccurrences++;
        }
        return characterOccurrences;
    }

    private static class CharacterOccurrence {

        private int numberOfLeftOccurrences = 0;
        private int numberOfRightOccurrences = 0;

        private boolean equalOccurrences() {
            return numberOfLeftOccurrences == numberOfRightOccurrences;
        }

    }

}
