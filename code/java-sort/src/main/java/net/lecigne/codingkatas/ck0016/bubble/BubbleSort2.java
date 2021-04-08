package net.lecigne.codingkatas.ck0016.bubble;

import net.lecigne.codingkatas.ck0016.Sort;

public class BubbleSort2 implements Sort {

    @Override
    public void sort(String[] names) {
        int n = names.length;
        while (n >= 2) {
            for (int i = 0; i < n - 1; i++) {
                if (names[i].compareTo(names[i + 1]) > 0) {
                    swap(names, i, i + 1);
                }
            }
            n--;
        }
    }

    private void swap(String[] arr, int index1, int index2) {
        final String tmp = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = tmp;
    }

}
