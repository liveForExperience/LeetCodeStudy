package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/18 12:37
 */
public class Interview_14II_3 {
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }

        long ans = 1;
        while (n > 4) {
            n -= 3;
            ans = ans * 3 % 1000000007;
        }

        return (int)(ans * n % 1000000007);
    }
}
