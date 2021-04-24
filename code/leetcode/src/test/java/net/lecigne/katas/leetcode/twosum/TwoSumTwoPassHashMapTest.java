package net.lecigne.katas.leetcode.twosum;

import org.junit.jupiter.api.DisplayName;

@DisplayName("The two-pass hashtable implementation of the Two Sum algorithm")
class TwoSumTwoPassHashMapTest implements TwoSumTest<TwoSumTwoPassHashMap> {

    @Override
    public TwoSumTwoPassHashMap createTwoSum() {
        return new TwoSumTwoPassHashMap();
    }

}
