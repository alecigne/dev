package net.lecigne.codingkatas.ck0016;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public interface SortTest<T extends Sort> {

    T getSort();

    String[] SORTED_ARRAY = new String[]{"Abraham", "Dagobert", "Johnson", "Wilkinson", "Wilson"};
    String[] EMPTY_ARRAY = new String[]{"Abraham", "Dagobert", "Johnson", "Wilkinson", "Wilson"};

    @Test
    default void sort_givenArrayOfNames_shouldReturnSortedArray() {
        String[] arrayToSort = new String[]{"Johnson", "Wilson", "Wilkinson", "Abraham", "Dagobert"};
        getSort().sort(arrayToSort);
        assertThat(arrayToSort).isEqualTo(SORTED_ARRAY);
    }

    @Test
    default void sort_givenEmptyArray_shouldNotFail() {
        assertThatCode(() -> getSort().sort(EMPTY_ARRAY)).doesNotThrowAnyException();
    }

}
