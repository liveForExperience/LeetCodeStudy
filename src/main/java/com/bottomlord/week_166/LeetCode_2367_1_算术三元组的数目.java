package com.bottomlord.week_166;

/**
 * @author chen yue
 * @date 2022-09-14 05:58:27
 */
public class LeetCode_2367_1_算术三元组的数目 {
    public int arithmeticTriplets(int[] nums, int diff) {
        int ans = 0;
        boolean[] bucket = new boolean[201];
        for (int num : nums) {
            bucket[num] = true;
        }

        for (int num : nums) {
            if (num + diff <= 200 &&
                num + 2 * diff <= 200 &&
                bucket[num + diff] &&
                bucket[num + 2 * diff]) {
                ans++;
            }
        }

        return ans;
    }
}
