package com.bottomlord.week_073;

/**
 * @author ChenYue
 * @date 2020/12/3 8:48
 */
public class LeetCode_370_2 {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];
        for (int[] update : updates) {
            ans[update[0]] += update[2];

            if (update[1] < length - 1) {
                ans[update[1] + 1] -= update[2];
            }
        }

        for (int i = 1; i < length; i++) {
            ans[i] += ans[i - 1];
        }

        return ans;
    }
}