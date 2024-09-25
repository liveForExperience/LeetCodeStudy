package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/12 20:20
 */
public class LeetCode_201_1_数字范围按位与 {
    public int rangeBitwiseAnd(int m, int n) {
        int ans = m;
        for (int i = m + 1; i <= n; i++) {
            ans &= i;
            if (ans == 0 || i == Integer.MAX_VALUE) {
                break;
            }
        }

        return ans;
    }
}
