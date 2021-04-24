package net.lecigne.katas.leetcode.twosum;

import org.junit.jupiter.api.DisplayName;

@DisplayName("The brute force implementation of the Two Sum algorithm")
public class TwoSumBruteForceTest implements TwoSumTest<TwoSumBruteForce> {

    @Override
    public TwoSumBruteForce createTwoSum() {
        return new TwoSumBruteForce();
    }

}
