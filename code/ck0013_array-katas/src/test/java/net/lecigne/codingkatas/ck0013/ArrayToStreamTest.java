package net.lecigne.codingkatas.ck0013;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayToStreamTest {

    @Test
    void arraysStream_givenArray_shouldReturnStream() {
        Integer[] sourceArray = new Integer[]{1, 2, 3, 4, 5};
        Stream<Integer> actualStream = Arrays.stream(sourceArray, 1, 3);
        assertThat(actualStream).containsExactly(2, 3);
    }

}
