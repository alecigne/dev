package net.lecigne.codingkatas.ck0016.bubble;

import net.lecigne.codingkatas.ck0016.Sort;

public class BubbleSort3 implements Sort {

    @Override
    public void sort(String[] names) {
        int n = names.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (names[j - 1].compareTo(names[j]) > 0) {
                    this.swap(names, j - 1, j);
                }
            }
        }
    }
    
    private void swap(String[] arr, int index1, int index2) {
        final String tmp = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = tmp;
    }

}
