package net.lecigne.codingkatas.ck0016.bubble;

import net.lecigne.codingkatas.ck0016.SortTest;

class BubbleSortRecursiveTest implements SortTest<BubbleSortRecursive> {

    @Override
    public BubbleSortRecursive getSort() {
        return new BubbleSortRecursive();
    }

}
