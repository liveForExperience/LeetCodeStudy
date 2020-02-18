package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/18 20:39
 */
public class Interview_16_3 {
    public double myPow(double x, int n) {
        long exponent = n;

        if (x == 1 || exponent == 0) {
            return 1;
        }

        if (exponent < 0) {
            exponent = Math.abs(exponent);
            x = 1 / x;
        }

        double ans = 1;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                ans *= x;
            }

            x *= x;
            exponent >>>= 1;
        }

        return ans;
    }
}
