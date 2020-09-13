package net.lecigne.codingkatas.ck0013;

import java.util.Arrays;

public final class ArrayMergeUtils {

    private ArrayMergeUtils() {
    }

    public static int[] mergeWithForLoop(int[] array1, int[] array2) {
        int[] resultArray = new int[array1.length + array2.length];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = (i < array1.length ? array1[i] : array2[i - array1.length]);
        }
        return resultArray;
    }

    public static int[] mergeWithArraysSetAll(int[] array1, int[] array2) {
        int[] resultArray = new int[array1.length + array2.length];
        Arrays.setAll(resultArray, i -> (i < array1.length ? array1[i] : array2[i - array1.length]));
        return resultArray;
    }

    public static int[] mergeWithCopy(int[] array1, int[] array2) {
        int[] resultArray = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, resultArray, array1.length, array2.length);
        return resultArray;
    }

}
