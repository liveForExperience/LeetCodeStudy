package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/11 9:03
 */
public class Interview_64_1_等差数列求和 {
    public int sumNums(int n) {
        if (n == 0) {
            return 0;
        }

        return n + sumNums(n - 1);
    }
}
