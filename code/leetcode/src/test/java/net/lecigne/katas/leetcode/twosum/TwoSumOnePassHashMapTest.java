package net.lecigne.katas.leetcode.twosum;

import org.junit.jupiter.api.DisplayName;

@DisplayName("The one-pass hashtable implementation of the Two Sum algorithm")
class TwoSumOnePassHashMapTest implements TwoSumTest<TwoSumOnePassHashMap> {

    @Override
    public TwoSumOnePassHashMap createTwoSum() {
        return new TwoSumOnePassHashMap();
    }

}
