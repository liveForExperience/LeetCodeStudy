package com.bottomlord.week_181;

/**
 * @author chen yue
 * @date 2022-12-31 14:03:38
 */
public class LeetCode_2455_1_可被三整除的偶数的平均值 {
    public int averageValue(int[] nums) {
        int sum = 0, count = 0;
        for (int num : nums) {
            if (num % 6 == 0) {
                sum += num;
                count++;
            }
        }

        return count == 0 ? 0 : sum / count;
    }
}
