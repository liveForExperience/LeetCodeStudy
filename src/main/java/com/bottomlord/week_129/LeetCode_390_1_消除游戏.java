package com.bottomlord.week_129;

/**
 * @author chen yue
 * @date 2022-01-02 20:19:57
 */
public class LeetCode_390_1_消除游戏 {
    public int lastRemaining(int n) {
        boolean left = true;
        int head = 1, step = 1;
        while (n > 1) {
            if (left || n % 2 == 1) {
                head += step;
            }
            step <<= 1;
            n >>= 1;
            left = !left;
        }

        return head;
    }
}
