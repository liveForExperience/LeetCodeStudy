package com.bottomlord.week_032;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/2/10 19:39
 */
public class LeetCode_416_2 {
    public boolean canPartition(int[] nums) {
        if (nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }

        Set<Integer> numSet = new HashSet<>();
        Set<Integer> indexSet = new HashSet<>();

        return backTrack(sum / 2, nums, numSet, indexSet);
    }

    private boolean backTrack(int num, int[] nums, Set<Integer> numSet, Set<Integer> indexSet) {
        if (num == 0) {
            return true;
        }

        for (int i = 0; i < nums.length; i++) {
            if (indexSet.contains(i)) {
                continue;
            }

            int a = num - nums[i];

            if (a < 0 || numSet.contains(a)) {
                continue;
            }

            if (a == 0) {
                return true;
            }

            numSet.add(a);
            indexSet.add(i);
            if (backTrack(a, nums, numSet, indexSet)) {
                return true;
            }
            indexSet.remove(i);
        }

        return false;
    }
}