package string;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class MessageCostCalculator {

    private final double ucs2MessageCost;
    private final int ucs2MessageLength;
    private final double gsm7MessageCost;
    private final int gsm7MessageLength;

    public double calculateMinCost(List<String> messages) {
        if (messages == null) {
            throw new IllegalArgumentException("Messages cannot be null");
        }
        return messages.stream()
                .map(this::cost)
                .reduce(0.0D, Double::sum);
    }

    // time complexity O(n), n - message length
    // memory complexity O(n), n - message length
    private double cost(String message) {
        double cost = 0.0D;
        int charIndex = 0;
        int lastSentCharIndex = 0;
        int subMessageMaxLength = gsm7MessageLength;
        while (charIndex < message.length()) {
            if (!gsm7Character(message.charAt(charIndex))) {
                subMessageMaxLength = ucs2MessageLength;
            }
            int subMessageLength = charIndex - lastSentCharIndex + 1;
            if ((charIndex == message.length() - 1) || (subMessageLength == subMessageMaxLength)) {
                log.trace("Send message '{}' length {}",
                        message.substring(lastSentCharIndex, charIndex + 1), subMessageLength);
                cost += subMessageMaxLength == ucs2MessageLength ? ucs2MessageCost : gsm7MessageCost;
                lastSentCharIndex += subMessageLength;
                subMessageMaxLength = gsm7MessageLength;
                charIndex++;
            } else if (subMessageLength > subMessageMaxLength) {
                log.trace("Flush GSM7 message '{}' length {}",
                        message.substring(lastSentCharIndex, charIndex), (subMessageLength - 1));
                cost += gsm7MessageCost;
                lastSentCharIndex += (charIndex - lastSentCharIndex);
            } else {
                charIndex++;
            }
        }
        return cost;
    }

    private boolean gsm7Character(char character) {
        return ' ' == character
                || ('0' <= character && character <= '9')
                || ('a' <= character && character <= 'z')
                || ('A' <= character && character <= 'Z');
    }

}