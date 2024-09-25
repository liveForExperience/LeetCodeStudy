package com.bottomlord.week_011;

public class LeetCode_260_3 {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        xor &= -xor;
        int[] ans = new int[2];
        for (int num : nums) {
            if ((xor & num) == 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }

        return ans;
    }
}