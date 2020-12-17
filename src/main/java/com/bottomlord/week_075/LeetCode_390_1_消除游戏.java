package com.bottomlord.week_075;

/**
 * @author ChenYue
 * @date 2020/12/17 18:47
 */
public class LeetCode_390_1_消除游戏 {
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining(n / 2));
    }
}
