package com.bottomlord.week_190;

/**
 * @author chen yue
 * @date 2023-03-06 16:05:09
 */
public class LeetCode_982_1_按位与为0的三元组 {
    public int countTriplets(int[] nums) {
        int[] arr = new int[1 << 16];
        for (int a : nums) {
            for (int b : nums) {
                arr[a & b]++;
            }
        }

        int ans = 0;
        for (int num : nums) {
            for (int i = 0; i < (1 << 16); i++) {
                if ((i & num) == 0) {
                    ans += arr[i];
                }
            }
        }

        return ans;
    }
}