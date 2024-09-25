package com.bottomlord.week_047;

/**
 * @author ChenYue
 * @date 2020/5/27 8:34
 */
public class LeetCode_974_1_和可被K整除的子数组 {
    public int subarraysDivByK(int[] A, int K) {
        int sum = 0, len = A.length, ans = 0;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (sum += A[i]);
        }

        for (int i = 0; i < len; i++) {
            int a = arr[i];
            if (a % K == 0) {
                ans++;
            }

            for (int j = i + 1; j < len; j++) {
                if ((arr[j] - a) % K == 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
