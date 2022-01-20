package com.bottomlord.week_132;

/**
 * @author chen yue
 * @date 2022-01-20 08:48:41
 */
public class LeetCode_2029_1_石子游戏 {
    public boolean stoneGameIX(int[] stones) {
        int[] arr = new int[3];
        for (int stone : stones) {
            arr[stone % 3]++;
        }

        if (arr[0] % 2 == 0) {
            if (arr[1] == 0 || arr[2] == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return Math.abs(arr[1] - arr[2]) > 2;
        }
    }
}
