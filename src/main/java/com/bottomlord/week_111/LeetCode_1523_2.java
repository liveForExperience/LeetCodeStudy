package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-23 08:43:00
 */
public class LeetCode_1523_2 {
    public int countOdds(int low, int high) {
        int num = high - low + 1;
        return num % 2 == 0 ? num / 2 : low % 2 == 1 ? num / 2 + 1 : num / 2;
    }
}
