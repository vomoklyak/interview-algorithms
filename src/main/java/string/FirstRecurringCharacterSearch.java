package string;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FirstRecurringCharacterSearch {

    public static Optional<Character> search(String string) {
        if (string == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        Set<Character> characters = new HashSet<>();
        for (char character : string.toCharArray()) {
            if (!characters.add(character)) {
                return Optional.of(character);
            }
        }
        return Optional.empty();
    }

}