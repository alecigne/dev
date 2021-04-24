package net.lecigne.katas.leetcode.twosum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public interface TwoSumTest<T extends TwoSum> {

    T createTwoSum();

    @DisplayName("should find indexes of numbers that add up to the target")
    @ParameterizedTest(name = "In array {0} the target {1} should be obtained by summing indexes {2}")
    @MethodSource("supplyArgs")
    default void twoSum_shouldWork(int[] numbers, int target, int[] expected) {
        int[] actualIndices = createTwoSum().twoSum(numbers, target);
        assertThat(actualIndices).containsExactly(expected);
    }

    static Stream<Arguments> supplyArgs() {
        return Stream.of(
                Arguments.of(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
                Arguments.of(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
                Arguments.of(new int[]{3, 3}, 6, new int[]{0, 1})
        );
    }

}
