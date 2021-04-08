package net.lecigne.codingkatas.ck0016.bubble;

import net.lecigne.codingkatas.ck0016.Sort;

public class BubbleSortRecursive implements Sort {

    @Override
    public void sort(String[] names) {
        bubbleSortRec(names, names.length);
    }

    private static void bubbleSortRec(String[] names, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            if (names[i].compareTo(names[i + 1]) > 0) {
                String tmp = names[i + 1];
                names[i + 1] = names[i];
                names[i] = tmp;
            }
        }
        bubbleSortRec(names, n - 1);
    }

}
