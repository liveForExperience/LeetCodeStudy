package com.bottomlord.week_197;

/**
 * @author chen yue
 * @date 2023-04-21 23:13:15
 */
public class LeetCode_2644_1_找出可整除性得分最大的整数 {
    public int maxDivScore(int[] nums, int[] divisors) {
        int max = 0, ans = Integer.MAX_VALUE;
        for (int divisor : divisors) {
            int cnt = 0;
            for (int num : nums) {
                if (num % divisor == 0) {
                    cnt++;
                }
            }

            if (cnt > max) {
                max = cnt;
                ans = divisor;
            }

            if (cnt == max) {
                ans = Math.min(divisor, ans);
            }
        }

        return ans;
    }
}
