package net.lecigne.katas.leetcode.twosum;

import java.util.HashMap;
import java.util.Map;

public class TwoSumTwoPassHashMap implements TwoSum {

    @Override
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValueMap = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            indexByValueMap.put(nums[index], index);
        }
        for (int index = 0; index < nums.length; index++) {
            int complement = target - nums[index];
            if (indexByValueMap.containsKey(complement)) {
                int complementIndex = indexByValueMap.get(complement);
                // In case the target is two times the value at the current index
                if (index == complementIndex) {
                    continue;
                }
                return new int[]{index, complementIndex};
            }
        }
        return new int[0];
    }

}
