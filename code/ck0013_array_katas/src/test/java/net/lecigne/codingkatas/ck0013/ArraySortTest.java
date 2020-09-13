package net.lecigne.codingkatas.ck0013;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class ArraySortTest {

    @Test
    void arraysSort_givenArrayOfIntegerPrimitives_shouldSortArrayInPlace() {
        int[] arrayToSort = new int[]{5, 2, 1, 4, 8};
        final int[] arrayExpected = new int[]{1, 2, 4, 5, 8};
        Arrays.sort(arrayToSort);
        assertThat(arrayToSort).isEqualTo(arrayExpected);

    }

    @Test
    void arraysSort_givenArrayOfIntegerObjects_shouldSortArrayInPlace() {
        Integer[] arrayToSort = new Integer[]{5, 2, 1, 4, 8};
        final Integer[] arrayExpected = new Integer[]{1, 2, 4, 5, 8};
        Arrays.sort(arrayToSort);
        assertThat(arrayToSort).isEqualTo(arrayExpected);
    }

    @Test
    void arraysSort_givenArrayOfStrings_shouldSortArrayInPlace() {
        String[] arrayToSort = new String[]{"A", "E", "M", "S", "Z", "B", "C"};
        final String[] arrayExpected = new String[]{"A", "B", "C", "E", "M", "S", "Z"};
        Arrays.sort(arrayToSort);
        assertThat(arrayToSort).isEqualTo(arrayExpected);
    }

    @Test
    void arraysSort_givenArrayOfStringsAndComparator_shouldSortArrayInPlaceReversed() {
        String[] arrayToSort = new String[]{"A", "E", "M", "S", "Z", "B", "C"};
        final String[] arrayExpected = new String[]{"Z", "S", "M", "E", "C", "B", "A"};
        Arrays.sort(arrayToSort, 0, arrayToSort.length, Comparator.comparing(String::toString).reversed());
        assertThat(arrayToSort).isEqualTo(arrayExpected);
    }

    @Test
    void arraysSort_givenArrayOfStringsAndComparator_shouldSortArrayInPlacePartiallyReversed() {
        String[] arrayToSort = new String[]{"A", "E", "M", "S", "Z", "B", "C"};
        final String[] arrayExpected = new String[]{"A", "Z", "S", "M", "E", "B", "C"};
        Arrays.sort(arrayToSort, 1, 5, Comparator.comparing(String::toString).reversed());
        assertThat(arrayToSort).isEqualTo(arrayExpected);
    }

}
