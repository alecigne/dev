package net.lecigne.katas.leetcode.twosum;

import java.util.HashMap;
import java.util.Map;

public class TwoSumOnePassHashMap implements TwoSum {

    @Override
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValueMap = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            int complement = target - nums[index];
            if (indexByValueMap.containsKey(complement)) {
                return new int[]{indexByValueMap.get(complement), index};
            }
            indexByValueMap.put(nums[index], index);
        }
        return new int[0];
    }

}
