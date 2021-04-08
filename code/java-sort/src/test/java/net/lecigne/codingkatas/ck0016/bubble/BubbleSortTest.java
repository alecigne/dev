package net.lecigne.codingkatas.ck0016.bubble;

import net.lecigne.codingkatas.ck0016.SortTest;

@SuppressWarnings("squid:S2187")
class BubbleSortTest implements SortTest<BubbleSort> {

    @Override
    public BubbleSort getSort() {
        return new BubbleSort();
    }

}
