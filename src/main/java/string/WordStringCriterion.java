package string;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@Slf4j
@NoArgsConstructor
public final class WordStringCriterion {

    public static boolean check(String string, Function<String, Boolean> wordChecker) {
        return check(string, 0, string.length(), wordChecker);
    }

    private static boolean check(String string, int startIndex, int endIndex, Function<String, Boolean> wordChecker) {
        if (startIndex == endIndex) {
            log.trace("String end was reached startIndex {}, endIndex{}", startIndex, endIndex);
            return false;
        }
        log.trace("Break into words substring {}", string.substring(startIndex, endIndex));
        if (wordChecker.apply(string.substring(startIndex, endIndex)) &&
                ((endIndex == string.length()) || check(string, endIndex, string.length(), wordChecker))) {
            return true;
        } else {
            return check(string, startIndex, endIndex - 1, wordChecker);
        }
    }

}