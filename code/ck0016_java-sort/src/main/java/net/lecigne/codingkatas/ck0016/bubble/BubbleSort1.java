package net.lecigne.codingkatas.ck0016.bubble;

import net.lecigne.codingkatas.ck0016.Sort;

public class BubbleSort1 implements Sort {

    @Override
    public void sort(String[] names) {
        int n = names.length;
        while (n >= 2) {
            for (int i = 0; i < n - 1; i++) {
                if (names[i].compareTo(names[i + 1]) > 0) {
                    final String tmp = names[i + 1];
                    names[i + 1] = names[i];
                    names[i] = tmp;
                }
            }
            n--;
        }
    }

}
