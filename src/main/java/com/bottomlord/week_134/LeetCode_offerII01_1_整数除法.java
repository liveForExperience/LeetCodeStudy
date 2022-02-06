package com.bottomlord.week_134;

/**
 * @author chen yue
 * @date 2022-02-04 10:38:47
 */
public class LeetCode_offerII01_1_整数除法 {
    public int divide(int a, int b) {
        long x = a, y = b;
        x = x < 0 ? -x : x;
        y = y < 0 ? -y : y;

        int sign = (x > 0) ^ (y > 0) ? -1 : 1;
        return (int) cal(a, b) * sign;
    }

    private long cal(long a, long b) {
        if (a < b) {
            return 0L;
        }

        long count = 0, bit = 1, c = b;
        while (a > 0) {
            if (a - c < 0) {
                break;
            }
            a -= c;
            c <<= 1;
            count += bit;
            bit <<= 1;
        }

        return count + cal(a, b);
    }
}
