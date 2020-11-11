package net.lecigne.codingkatas.ck0013;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayCopyTest {

    private StringBuffer[] originalArray;
    private StringBuffer[] copiedArray;

    @BeforeEach
    void setUp() {
        originalArray = new StringBuffer[]{new StringBuffer("hello"), new StringBuffer("world")};
        copiedArray = new StringBuffer[originalArray.length];
    }

    @Test
    void systemArraycopy_shouldProduceExactCopy() {
        // When
        System.arraycopy(originalArray, 0, copiedArray, 0, originalArray.length);

        // Then
        assertThat(copiedArray).isEqualTo(originalArray);
    }

    @Test
    void systemArraycopy_givenArrayOfObjects_shouldProduceShallowCopy() {
        // When
        System.arraycopy(originalArray, 0, copiedArray, 0, originalArray.length);
        originalArray[0].setLength(3);
        String expected = "hel";

        // Then
        assertThat(originalArray[0]).hasToString(expected);
        assertThat(copiedArray[0]).hasToString(expected);
    }

    @Test
    void arraysCopyOf_shouldProduceExactCopy() {
        // When
        copiedArray = Arrays.copyOf(originalArray, originalArray.length);

        // Then
        assertThat(copiedArray).isEqualTo(originalArray);
    }

    @Test
    void ArraysCopyOf_givenArrayOfObjects_shouldProduceShallowCopy() {
        // When
        copiedArray = Arrays.copyOf(originalArray, originalArray.length);
        originalArray[0].setLength(3);
        String expected = "hel";

        // Then
        assertThat(originalArray[0]).hasToString(expected);
        assertThat(copiedArray[0]).hasToString(expected);
    }

}
