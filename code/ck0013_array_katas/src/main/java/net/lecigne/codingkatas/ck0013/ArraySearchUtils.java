package net.lecigne.codingkatas.ck0013;

import java.util.Arrays;

/**
 * Search methods on a simple integer array.
 */
public final class ArraySearchUtils {

    private ArraySearchUtils() {
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    public static boolean searchWithForLoop(int value, int[] array) {
        boolean found = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                found = true;
                break;
            }
        }
        return found;
    }

    public static boolean searchWithForEach(int value, int[] array) {
        boolean found = false;
        for (int n : array) {
            if (n == value) {
                found = true;
                break;
            }
        }
        return found;
    }

    public static boolean searchWithStreamFilter(int value, int[] array) {
        return Arrays.stream(array).anyMatch(element -> element == value);
    }

    public static boolean searchWithBinarySearch(int value, int[] array) {
        int[] copiedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(copiedArray);
        return Arrays.binarySearch(copiedArray, value) >= 0;
    }

}
