package com.bottomlord.week_084;

/**
 * @author ChenYue
 * @date 2021/2/19 9:19
 */
public class LeetCode_1004_2 {
    public int longestOnes(int[] A, int K) {
        int len = A.length, l = 0, lsum = 0, rsum = 0, ans = 0;
        for (int r = 0; r < len; r++) {
            rsum += 1 - A[r];
            while (rsum - lsum > K) {
                lsum += 1 - A[l];
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }
}
