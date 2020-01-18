package com.bottomlord.week_028;

/**
 * @author ThinkPad
 * @date 2020/1/18 10:56
 */
public class LeetCode_477_1_汉明距离总和 {
    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int num = nums[i] ^ nums[j];
                while (num != 0) {
                    num = (num - 1) & num;
                    ans++;
                }
            }
        }

        return ans;
    }
}
