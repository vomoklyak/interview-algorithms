package string;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderedParenthesesCriterion {

    public static boolean orderedParentheses(String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("Expression cannot be null");
        }
        LinkedList<Character> parentheses = new LinkedList<>();
        for (char character : expression.toCharArray()) {
            if (leftParentheses(character)) {
                parentheses.addFirst(character);
            } else if (parentheses.isEmpty() || !orderedParentheses(parentheses.pollFirst(), character)) {
                return false;
            }
        }
        return true;
    }

    private static boolean leftParentheses(char character) {
        return character == '(' || character == '[' || character == '{';
    }

    private static boolean orderedParentheses(char leftChar, char rightChar) {
        return (leftChar == '(' && rightChar == ')')
                || (leftChar == '[' && rightChar == ']')
                || (leftChar == '{' && rightChar == '}');
    }

}