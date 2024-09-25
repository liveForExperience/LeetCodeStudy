package com.bottomlord.week_038;

/**
 * @author ChenYue
 * @date 2020/3/29 21:38
 */
public class Interview_0801_2 {
    public int waysToStep(int n) {
        if (n < 3) {
            return n;
        }

        if (n == 3) {
            return 4;
        }

        int a = 1, b = 2, c = 4;

        for (int i = 4; i <= n; i++) {
            int ans = a;
            ans = (ans + b) % 1000000007;
            ans = (ans + c) % 1000000007;

            a = b;
            b = c;
            c = ans;
        }

        return c;
    }
}
