package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/18 13:01
 */
public class Interview_16_1_数值的整数次方 {
    public double myPow(double x, int n) {
        boolean f = n < 0;
        int num = Math.abs(n);
        double ans = 1.0;
        for (int i = 0; i < num; i++) {
            ans *= f ? (1 / x) : x;
        }

        return ans;
    }
}
