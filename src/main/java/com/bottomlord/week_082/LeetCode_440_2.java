package com.bottomlord.week_082;

/**
 * @author ChenYue
 * @date 2021/2/4 8:50
 */
public class LeetCode_440_2 {
    public int findKthNumber(int n, int k) {
        int cur = 1;
        while (k > 0) {
            int step = step(cur, cur + 1, n);

            if (step < k) {
                cur++;
                k -= step;
            } else {
                cur *= 10;
                k--;
            }
        }
        return cur;
    }

    private int step(long cur, long next, int n) {
        int step = 0;
        while (cur <= n) {
            step += Math.min(n + 1, next) - cur;
            cur *= 10;
            next *= 10;
        }
        return step;
    }
}
