package net.lecigne.codingkatas.ck0013;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ArrayToListUtils {

    private ArrayToListUtils() {
    }

    public static List<Integer> arrayToListWithForLoop(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int number : array) {
            list.add(number); // autoboxing for single values
        }
        return list;
    }

    public static List<Integer> arrayToListWithStreams(int[] array) {
        return Arrays.stream(array).boxed().collect(Collectors.toList());
    }

    public static List<Integer> arrayToListWithIntStreams(int[] array) {
        return IntStream.of(array).boxed().collect(Collectors.toList());
    }

}
