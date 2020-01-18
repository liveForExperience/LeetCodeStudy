package com.bottomlord.week_028;

/**
 * @author ThinkPad
 * @date 2020/1/18 11:18
 */
public class LeetCode_477_2 {
    public int totalHammingDistance(int[] nums) {
        int ans = 0, n = nums.length;
        int[] arr = new int[32];

        for (int num : nums) {
            int index = 0;
            while (num > 0) {
                arr[index] += num & 1;
                num >>= 1;
                index++;
            }
        }

        for (int num : arr) {
            ans += num * (n - num);
        }

        return ans;
    }
}