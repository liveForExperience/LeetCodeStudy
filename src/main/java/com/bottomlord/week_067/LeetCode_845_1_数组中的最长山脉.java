package com.bottomlord.week_067;

public class LeetCode_845_1_数组中的最长山脉 {
    public int longestMountain(int[] A) {
        int len = A.length;
        if (len < 3) {
            return 0;
        }

        int[] left = new int[len];
        for (int i = 1; i < len; i++) {
            left[i] = A[i] > A[i - 1] ? left[i - 1] + 1 : 0;
        }

        int[] right = new int[len];
        for (int i = len - 2; i >= 0; i--) {
            right[i] = A[i] > A[i + 1] ? right[i + 1] + 1 : 0;
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(left[i] + right[i] + 1, ans);
            }
        }

        return ans;
    }
}
