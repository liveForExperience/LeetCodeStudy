package com.bottomlord.week_141;

/**
 * @author chen yue
 * @date 2022-03-23 09:33:57
 */
public class LeetCode_440_1_字典序的第K小数字 {
    public int findKthNumber(int n, int k) {
        int cur = 1;
        k--;
        while (k > 0) {
            int step = step(cur, cur + 1, n);

            if (step > k) {
                cur *= 10;
                k--;
            } else {
                cur++;
                k -= step;
            }
        }

        return cur;
    }

    private int step(int cur, int next, int n) {
        int step = 0;
        while (cur <= n) {
            step += Math.min(n + 1, next) - cur;

            cur *= 10;
            next *= 10;
        }

        return step;
    }
}
