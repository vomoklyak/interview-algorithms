package graph;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserGroupSearchTest {

    @Test
    public void shouldSearchNumberOfUserGroupsCaseEmptyRelations() {
        // Given
        final List<String> relations = new ArrayList<>();

        // When
        final int result = UserGroupSearch.searchNumberOfUserGroups(relations);

        // Then
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldSearchNumberOfUserGroups() {
        // Given
        final List<String> relations = Arrays.asList(
                "1100",
                "1110",
                "0110",
                "0001"
        );

        // When
        final int result = UserGroupSearch.searchNumberOfUserGroups(relations);

        // Then
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void shouldSearchNumberOfUserGroupsCaseOneGroup() {
        // Given
        final List<String> relations = Arrays.asList(
                "1100",
                "1110",
                "0011",
                "0011"
        );

        // When
        final int result = UserGroupSearch.searchNumberOfUserGroups(relations);

        // Then
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldSearchNumberOfUserGroupsCaseDisjointGroups() {
        // Given
        final List<String> relations = Arrays.asList(
                "1000",
                "0100",
                "0010",
                "0001"
        );

        // When
        final int result = UserGroupSearch.searchNumberOfUserGroups(relations);

        // Then
        Assertions.assertThat(result).isEqualTo(4);
    }

}