package net.lecigne.codingkatas.ck0016.bubble;

public class BubbleSortDraft {

    public void sort2(String[] names) {
        int n = names.length;
        boolean swapNeeded = true;
        while (n >= 2 && swapNeeded) {
            swapNeeded = false;
            for (int j = 0; j < n - 1; j++) {
                if (names[j].compareTo(names[j + 1]) > 0) {
                    final String tmp = names[j + 1];
                    names[j + 1] = names[j];
                    names[j] = tmp;
                    swapNeeded = true;
                }
            }
            if (!swapNeeded) {
                break;
            }
            n--;
        }
    }

}
