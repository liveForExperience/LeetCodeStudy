package com.bottomlord.week_099;

public class LeetCode_LCP18_1_早餐组合 {
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        int count = 0;
        for (int s : staple) {
            for (int d : drinks) {
                if (s + d <= x) {
                    count = (count + 1) % 1000000007;
                }
            }
        }
        return count;
    }
}
