package com.bottomlord.week_144;

/**
 * @author chen yue
 * @date 2022-04-14 08:54:53
 */
public class LeetCode_2235_2 {
    public int sum(int num1, int num2) {
        while (num1 != 0) {
            int tmp = num1 ^ num2;
            num1 = (num1 & num2) << 1;
            num2 = tmp;
        }

        return num2;
    }
}
