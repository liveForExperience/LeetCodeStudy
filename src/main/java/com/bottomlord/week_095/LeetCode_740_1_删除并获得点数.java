package com.bottomlord.week_095;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/5/5 22:17
 */
public class LeetCode_740_1_删除并获得点数 {
    public int deleteAndEarn(int[] nums) {
        int max = Arrays.stream(nums).max().orElse(0);
        int[] sums = new int[max + 1];
        for (int num : nums) {
            sums[num] += num;
        }

        int one = sums[0], two = sums[1];
        for (int i = 2; i < sums.length; i++) {
            int tmp = two;
            two = Math.max(one + sums[i], two);
            one = tmp;
        }

        return two;
    }
}
