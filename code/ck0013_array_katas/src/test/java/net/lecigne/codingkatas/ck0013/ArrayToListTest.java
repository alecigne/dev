package net.lecigne.codingkatas.ck0013;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ArrayToListTest {

    @Test
    void arraysAsList_givenPrimitivesOrObjects_shouldReturnListOfObjects() {
        // Given
        List<Integer> listExpected1 = new ArrayList<>();
        listExpected1.add(0);
        listExpected1.add(1);
        listExpected1.add(2);

        List<String> listExpected2 = new ArrayList<>();
        listExpected2.add("0");
        listExpected2.add("1");
        listExpected2.add("2");

        // When
        List<Integer> listActual1 = Arrays.asList(0, 1, 2);
        List<String> listActual2 = Arrays.asList("0", "1", "2");

        // Then
        assertThat(listActual1).isEqualTo(listExpected1);
        assertThat(listActual2).isEqualTo(listExpected2);
    }

    /**
     * Drawbacks of Arrays.asList: it returns a java.util.Arrays$ArrayList (nested
     * class) which is a fixed-size list. You cannot add to it and you cannot remove
     * from it.
     */
    @Test
    void arraysAsList_givenElements_shouldReturnFixedSizeList() {
        // Given
        List<Integer> numbers = Arrays.asList(0, 1, 2);

        // When
        ThrowingCallable removeFromArray = () -> numbers.remove(0);
        ThrowingCallable addToArray = () -> numbers.add(6);

        // Then
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(removeFromArray);
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(addToArray);
    }

    @Test
    void arrayToListWithForLoop_shouldWork() {
        // Given
        int[] sourceArray = new int[]{0, 1, 2};
        List<Integer> expectedList = List.of(0, 1, 2);

        // When
        List<Integer> actualList = ArrayToListUtils.arrayToListWithForLoop(sourceArray);

        // Then
        assertThat(actualList).isEqualTo(expectedList);
    }

    @Test
    void arrayToListWithStreams_shouldWork() {
        // Given
        int[] sourceArray = new int[]{0, 1, 2};
        List<Integer> expectedList = List.of(0, 1, 2);

        // When
        List<Integer> actualList = ArrayToListUtils.arrayToListWithStreams(sourceArray);

        // Then
        assertThat(actualList).isEqualTo(expectedList);
    }

    @Test
    void arrayToListWithIntStreams_shouldWork() {
        // Given
        int[] sourceArray = new int[]{0, 1, 2};
        List<Integer> expectedList = List.of(0, 1, 2);

        // When
        List<Integer> actualList = ArrayToListUtils.arrayToListWithIntStreams(sourceArray);

        // Then
        assertThat(actualList).isEqualTo(expectedList);
    }

}
