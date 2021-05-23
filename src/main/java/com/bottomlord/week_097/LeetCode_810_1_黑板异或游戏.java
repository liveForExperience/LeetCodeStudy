package com.bottomlord.week_097;

/**
 * @author ChenYue
 * @date 2021/5/22 8:43
 */
public class LeetCode_810_1_黑板异或游戏 {
    public boolean xorGame(int[] nums) {
        if (nums.length % 2 == 0) {
            return true;
        }

        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        return xor == 0;
    }
}
