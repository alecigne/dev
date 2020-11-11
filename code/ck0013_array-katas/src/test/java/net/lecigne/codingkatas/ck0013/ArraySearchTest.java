package net.lecigne.codingkatas.ck0013;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArraySearchTest {

    private final int[] array = new int[]{5, 2, 1, 4, 8};

    @Test
    void searchWithForLoop_givenValueAndArray_shouldReturnTrue() {
        assertThat(ArraySearchUtils.searchWithForLoop(4, array)).isTrue();
    }

    @Test
    void searchWithForEach_givenValueAndArray_shouldReturnTrue() {
        assertThat(ArraySearchUtils.searchWithForEach(4, array)).isTrue();
    }

    @Test
    void searchWithStreamFilter_givenValueAndArray_shouldReturnTrue() {
        assertThat(ArraySearchUtils.searchWithStreamFilter(4, array)).isTrue();
    }

    /**
     * See also the "algorithms" section of this repo
     */
    @Test
    void arraysBinarySearch_givenSortedArray_shouldReturnIndex() {
        assertThat(ArraySearchUtils.searchWithBinarySearch(4, array));
    }

}
