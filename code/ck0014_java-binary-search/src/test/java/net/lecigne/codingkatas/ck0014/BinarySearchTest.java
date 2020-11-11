package net.lecigne.codingkatas.ck0014;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

interface BinarySearchTest<T extends BinarySearch> {

    T createSearch();

    int[] source = {0, 1, 12, 45, 178, 220};

    @Test
    default void search_givenTargetInSource_shouldReturnIndex() {
        int index = createSearch().search(source, 45);
        assertThat(index).isEqualTo(3);
    }

    @Test
    default void search_givenTargetNotInSource_shouldReturnMinus1() {
        int index = createSearch().search(source, 8);
        assertThat(index).isEqualTo(-1);
    }

}
