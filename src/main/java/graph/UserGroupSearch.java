package graph;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserGroupSearch {

    public static int searchNumberOfUserGroups(List<String> userRelations) {
        validRelations(userRelations);
        int numberOfUserGroups = 0;
        Set<Integer> seenUserIndices = new HashSet<>();
        for (int userIndex = 0; userIndex < userRelations.size(); userIndex++) {
            if (!seenUserIndices.contains(userIndex)) {
                log.trace("Search users from group {}", numberOfUserGroups);
                numberOfUserGroups++;
                searchUserGroup(0, userRelations, seenUserIndices);
            }

        }
        return numberOfUserGroups;
    }

    private static void searchUserGroup(int userIndex, List<String> relations, Set<Integer> seenUserIndices) {
        log.trace("Process user {} relations", userIndex);
        seenUserIndices.add(userIndex);
        String userRelations = relations.get(userIndex);
        IntStream.range(0, userRelations.length())
                .filter(adjacentUserIndex -> userRelations.charAt(adjacentUserIndex) == '1')
                .forEach(adjacentUserIndex -> {
                    if (!seenUserIndices.contains(adjacentUserIndex)) {
                        searchUserGroup(adjacentUserIndex, relations, seenUserIndices);
                    }
                });
    }

    private static void validRelations(List<String> relations) {
        if (relations.size() > 300) {
            throw new IllegalArgumentException("The number of relations should be less or equal than 300");
        }
    }

}