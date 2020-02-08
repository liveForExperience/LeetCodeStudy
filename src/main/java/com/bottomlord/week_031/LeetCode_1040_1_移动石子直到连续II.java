package com.bottomlord.week_031;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/2/8 21:40
 */
public class LeetCode_1040_1_移动石子直到连续II {
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int len = stones.length,
            max = (stones[len - 1] - stones[0] + 1 - len) - (Math.min(stones[len - 1] - stones[len - 2] - 1, stones[1] - stones[0] - 1)),
            right = 0, min = len;

        for (int left = 0; left < len; left++) {
            while (right + 1 < len && stones[right + 1] - stones[left] + 1 <= len) {
                right++;
            }

            int cost = len - (right - left + 1);
            if (right - left + 1 == len - 1 && stones[right] - stones[left] + 1 == len - 1) {
                cost = 2;
            }

            min = Math.min(cost, min);
        }

        return new int[]{min, max};
    }
}
