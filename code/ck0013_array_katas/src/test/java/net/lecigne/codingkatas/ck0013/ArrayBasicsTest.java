package net.lecigne.codingkatas.ck0013;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Arrays basics. This is so basic that most of the tests are ridiculous :) They will just pass if there is no problem.
 */
class ArrayBasicsTest {

    @SuppressWarnings({"unused", "CStyleArrayDeclaration", "squid:S2699"})
    @Test
    void declaration() {
        int[] array1;
        int array2[]; // "C-style", not recommended
    }

    @Test
    void initialization() {
        int[] array1 = new int[2]; // allocate memory for 2 integers
        assertThat(array1).hasSize(2);

        int[] array2 = new int[]{0, 1, 2, 3, 4};
        int[] array3 = {0, 1, 2, 3, 4};
        assertThat(array2).isEqualTo(array3);

//		This would be a syntactical error:
//		int[] array4;
//		array4 = { 0, 1, 2 };

//		And this would too:
//		return { 0, 1, 2 };

//      However, this works:
//      return new int[]{0, 1, 2};
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void access() {
        int[] array1 = {1, 2};
        assertThat(array1[0]).isEqualTo(1);

        array1[0] = 3;
        array1[1] = 4;
        assertThat(array1[0]).isEqualTo(3);
        assertThat(array1[1]).isEqualTo(4);

        Executable exec = () -> array1[2] = 3;
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, exec);
    }

    /**
     * Iteration is illustrated by storing elements in a list.
     */
    @SuppressWarnings({"ForLoopReplaceableByForEach"})
    @Test
    void iterating() {
        int[] array = {0, 1, 2, 3, 4};

        List<Integer> arrayList1 = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            arrayList1.add(array[i]);
        }
        assertThat(arrayList1).hasSize(5);

        List<Integer> arrayList2 = new ArrayList<>();
        for (int element : array) {
            arrayList2.add(element);
        }
        assertThat(arrayList2).hasSize(5);

        // Accumulating in a list can be a one-liner - the goal here is to show iteration with a stream
        List<Integer> arrayList3 = new ArrayList<>();
        Arrays.stream(array).forEach(arrayList3::add);
        assertThat(arrayList3).hasSize(5);

        List<Integer> arrayList4 = new ArrayList<>();
        IntStream.of(array).forEach(arrayList4::add);
        assertThat(arrayList4).hasSize(5);
    }

    @Test
    void varargs() {
        String[] ingredients = varargMethod("Milk", "Tomato", "Chips");
        assertThat(ingredients).hasSize(3);

        // An array can still be passed as long as the types match
        String[] groceries = new String[]{"Milk", "Tomato", "Chips"};
        String[] ingredients2 = varargMethod(groceries);
        assertThat(ingredients2).hasSize(3);
    }

    private String[] varargMethod(String... varargs) {
        return varargs;
    }

}
