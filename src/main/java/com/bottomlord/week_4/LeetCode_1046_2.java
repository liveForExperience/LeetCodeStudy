package com.bottomlord.week_4;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/7/30 20:58
 */
public class LeetCode_1046_2 {
    public int lastStoneWeight(int[] stones) {
        int len = stones.length;
        for (int i = 0; i < len - 1; i++) {
            Arrays.sort(stones);
            int num = stones[len - 1] - stones[len - 2];
            stones[len - 1] = num;
            stones[len - 2] = 0;
        }

        return stones[len - 1];
    }
}