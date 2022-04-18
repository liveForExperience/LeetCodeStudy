package com.bottomlord.week_145;

/**
 * @author chen yue
 * @date 2022-04-18 20:13:15
 */
public class LeetCode_LCP50_1_宝石补给 {
    public int giveGem(int[] gem, int[][] operations) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int[] operation : operations) {
            int x = operation[0], y = operation[1];
            int count = gem[x] / 2;
            gem[x] -= count;
            gem[y] += count;
        }

        for (int num : gem) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return max - min;
    }
}
