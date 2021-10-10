package com.bottomlord.week_117;

/**
 * @author chen yue
 * @date 2021-10-10 10:01:47
 */
public class LeetCode_441_1_排列硬币 {
    public int arrangeCoins(int n) {
        long index = 1;
        int count = 1;
        while (index <= n) {
            index += ++count;
        }
        return count - 1;
    }
}
