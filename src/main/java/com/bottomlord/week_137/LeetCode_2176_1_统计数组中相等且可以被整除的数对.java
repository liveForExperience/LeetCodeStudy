package com.bottomlord.week_137;

/**
 * @author chen yue
 * @date 2022-02-25 09:17:38
 */
public class LeetCode_2176_1_统计数组中相等且可以被整除的数对 {
    public int countPairs(int[] nums, int k) {
        int len = nums.length, count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
