package com.bottomlord.week_084;

/**
 * @author ChenYue
 * @date 2021/2/19 8:23
 */
public class LeetCode_1004_1_最大连续1的个数III {
    public int longestOnes(int[] A, int K) {
        int len = A.length, ans = 0;
        for (int i = 0; i < len; i++) {
            A[i] ^= 1;
        }

        int[] sum = new int[len + 1];
        for (int i = 1; i < len + 1; i++) {
            sum[i] = sum[i - 1] + A[i - 1];
        }

        for (int r = 0; r < len ; r++) {
            int target = sum[r + 1] - K;
            int l = binarySearch(target, sum);
            ans = Math.max(ans, r - l  + 1);
        }

        return ans;
    }

    private int binarySearch(int target, int[] sum) {
        int l = 0, r = sum.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (sum[mid + 1] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}
