package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-22 21:28:22
 */
public class LeetCode_1512_1_好数对的数目 {
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
