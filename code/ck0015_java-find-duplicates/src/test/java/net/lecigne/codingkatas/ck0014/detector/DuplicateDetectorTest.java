package net.lecigne.codingkatas.ck0014.detector;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

interface DuplicateDetectorTest<T extends DuplicateDetector> {

    T getAnInstance();

    @ParameterizedTest
    @MethodSource("provideArgs")
    default void containsDuplicate_givenArrayOfInts_shouldDetectDuplicates(int[] numbers, boolean expected) {
        assertThat(getAnInstance().containsDuplicate(numbers)).isEqualTo(expected);
    }

    static Stream<Arguments> provideArgs() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 1}, true),
                Arguments.of(new int[]{1, 2, 3, 4}, false),
                Arguments.of(new int[]{-2, 0, Integer.MAX_VALUE, -2}, true));
    }

}
