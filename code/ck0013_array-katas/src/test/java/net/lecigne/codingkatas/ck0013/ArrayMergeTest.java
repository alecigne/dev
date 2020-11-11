package net.lecigne.codingkatas.ck0013;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayMergeTest {

    private final int[] arrayHalf1 = new int[]{5, 2, 1, 4, 8};
    private final int[] arrayHalf2 = new int[]{10, 4, 9, 11, 2};
    private final int[] arrayExpected = new int[]{5, 2, 1, 4, 8, 10, 4, 9, 11, 2};

    @Test
    void merge_givenTwoArrays_shouldReturnCombinationOfArrays() {
        int[] arrayTested = ArrayMergeUtils.mergeWithForLoop(arrayHalf1, arrayHalf2);
        assertThat(arrayTested).isEqualTo(arrayExpected);
    }

    @Test
    void mergeWithArraysSetAll_givenTwoArrays_shouldReturnCombinationOfArrays() {
        int[] arrayTested = ArrayMergeUtils.mergeWithArraysSetAll(arrayHalf1, arrayHalf2);
        assertThat(arrayTested).isEqualTo(arrayExpected);
    }

    @Test
    void mergeWithMergeWithCopy_givenTwoArrays_shouldReturnCombinationOfArrays() {
        int[] arrayTested = ArrayMergeUtils.mergeWithCopy(arrayHalf1, arrayHalf2);
        assertThat(arrayTested).isEqualTo(arrayExpected);
    }

}
