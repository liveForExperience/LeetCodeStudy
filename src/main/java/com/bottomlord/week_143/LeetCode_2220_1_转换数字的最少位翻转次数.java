package com.bottomlord.week_143;

/**
 * @author chen yue
 * @date 2022-04-07 08:42:05
 */
public class LeetCode_2220_1_转换数字的最少位翻转次数 {
    public int minBitFlips(int start, int goal) {
        int xor = start ^ goal;

        int bit = 1, count = 0;
        while (xor > 0) {
            if ((xor & bit) == 1) {
                count++;
            }

            xor >>= 1;
        }

        return count;
    }
}
