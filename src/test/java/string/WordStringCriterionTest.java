package string;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class WordStringCriterionTest {

    @Test
    public void shouldCheckIfWordString() {
        // Given
        final String string = "aabb";
        final Set<String> dictionary = new HashSet<>();
        dictionary.add("aa");
        dictionary.add("bb");

        // When
        final boolean result = WordStringCriterion.check(string, dictionary::contains);

        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldCheckIfWordStringCaseNotGreedy() {
        // Given
        final String string = "aabbcc";
        final Set<String> dictionary = new HashSet<>();
        dictionary.add("aa");
        dictionary.add("aabb");
        dictionary.add("bbcc");

        // When
        final boolean result = WordStringCriterion.check(string, dictionary::contains);

        // Then
        Assertions.assertThat(result).isTrue();
    }

}