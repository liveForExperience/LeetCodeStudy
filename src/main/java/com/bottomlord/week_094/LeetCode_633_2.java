package com.bottomlord.week_094;

/**
 * @author ChenYue
 * @date 2021/4/28 9:04
 */
public class LeetCode_633_2 {
    public boolean judgeSquareSum(int c) {
        int a = 0, b = (int) Math.sqrt(c);
        while (a <= b) {
            int x = (int)Math.pow(a, 2), y = (int)Math.pow(b, 2);
            if (x + y == c) {
                return true;
            } else if (x + y < c) {
                a++;
            } else {
                b--;
            }
        }
        return false;
    }
}
