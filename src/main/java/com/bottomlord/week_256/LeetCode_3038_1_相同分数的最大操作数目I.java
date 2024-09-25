package com.bottomlord.week_256;

/**
 * @author chen yue
 * @date 2024-06-07 17:12:48
 */
public class LeetCode_3038_1_相同分数的最大操作数目I {
    public int maxOperations(int[] nums) {
        int i = 2, n = nums.length, sum = nums[0] + nums[1], cnt = 1;
        while (i + 1 < n && nums[i] + nums[i + 1] == sum) {
            cnt++;
            i += 2;
        }
        return cnt;
    }
}
