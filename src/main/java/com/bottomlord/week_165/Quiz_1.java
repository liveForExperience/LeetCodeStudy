package com.bottomlord.week_165;

/**
 * @author chen yue
 * @date 2022-09-11 21:29:45
 */
public class Quiz_1 {
    public static int quiz(int[] nums, int K, int M) {
        int[] arr = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int num : nums) {
                arr[i] += (num >> i) & 1;
            }
        }

        int ans = 0, index = 0;
        for (int count : arr) {
            ans += ((count % M) / K) << index++;
        }

        return ans;
    }
}
