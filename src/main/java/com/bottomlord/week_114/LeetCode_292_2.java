package com.bottomlord.week_114;

/**
 * @author chen yue
 * @date 2021-09-18 09:04:09
 */
public class LeetCode_292_2 {
    public boolean canWinNim(int n) {
        if (n <= 3) {
            return true;
        }

        boolean x = true, y = true, z = true;
        for (int i = 4; i <= n; i++) {
            boolean flag = !x | !y | !z;
            x = y;
            y = z;
            z = flag;
        }

        return z;
    }
}
