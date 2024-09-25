package com.bottomlord.week_255;

/**
 * @author chen yue
 * @date 2024-06-01 14:26:13
 */
public class LeetCode_2928_1_给小朋友分糖果I {
    public int distributeCandies(int n, int limit) {
        return search(0, n, limit);
    }

    private int search(int cur, int left, int limit) {
        if (cur == 2) {
            return left >= 0 && left <= limit ? 1 : 0;
        }

        int sum = 0;
        for (int cnt = 0; cnt <= limit; cnt++) {
            sum += search(cur + 1, left - cnt, limit);
        }
        return sum;
    }
}
