package com.bottomlord.week_084;

/**
 * @author ChenYue
 * @date 2021/2/18 8:52
 */
public class LeetCode_995_2 {
    public int minKBitFlips(int[] A, int K) {
        int len = A.length, count = 0, ans = 0;
        int[] diff = new int[len + 2];

        for (int i = 0; i < len; i++) {
            count += diff[i + 1];
            if ((A[i] + count) % 2 == 0) {
                if (i + K > len) {
                    return -1;
                }

                diff[i + 1]++;
                diff[i + K + 1]--;
                count++;
                ans++;
            }
        }

        return ans;
    }
}
