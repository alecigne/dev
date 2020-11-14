package net.lecigne.codingkatas.ck0016.bubble;

import net.lecigne.codingkatas.ck0016.Sort;

/**
 * A non-generic, naive implementation of the Bubble sort algorithm.
 */
public class BubbleSort implements Sort {

    /**
     * Sort an array of strings using the Bubble sort algorithm.
     */
    @Override
    public void sort(String[] names) {
        // Get the last comparison index
        int lastIndex = names.length - 2;
        boolean isOrdered;
        while (lastIndex >= 0) {
            isOrdered = true;
            for (int index = 0; index <= lastIndex; index++) {
                if (isUnordered(names[index], names[index + 1])) {
                    isOrdered = false;
                    swap(names, index, index + 1);
                }
            }
            if (isOrdered) {
                break;
            }
            lastIndex--;
        }
    }

    /**
     * Check if the two strings are unordered.
     */
    private boolean isUnordered(String first, String second) {
        return first.compareTo(second) > 0;
    }

    /**
     * Swap two strings in an array of strings.
     */
    private void swap(String[] names, int index1, int index2) {
        String tmp = names[index2];
        names[index2] = names[index1];
        names[index1] = tmp;
    }

}
