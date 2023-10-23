package com.bottomlord.week_223;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-10-22 21:19:10
 */
public class LeetCode_1402_1_做菜顺序 {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int sum = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            sum += satisfaction[i];
            ans += (i + 1) * satisfaction[i];
        }

        for (int num : satisfaction) {
            if (ans >= ans - sum) {
                break;
            }

            ans -= (ans - sum);
            sum -= num;
        }

        return ans;
    }
}
