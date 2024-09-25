package com.bottomlord.week_016;

public class LeetCode_667_1_优美的排列II {
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int num = k;
        for (int i = 0; i < n; i++) {
            if (k + 1 == num / 2) {
                ans[i] = i + 1;
            } else {
                if (i % 2 == 0) {
                    ans[i] = num - k + 1;
                } else {
                    ans[i] = k-- + 1;
                }
            }
        }
        return ans;
    }
}
