package com.bottomlord.week_098;

/**
 * @author ChenYue
 * @date 2021/5/28 8:15
 */
public class LeetCode_477_1_汉明距离总和 {
    public int totalHammingDistance(int[] nums) {
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int num : nums) {
                count += (num >> i) & 1;
            }

            sum += count * (nums.length - count);
        }
        return sum;
    }
}
