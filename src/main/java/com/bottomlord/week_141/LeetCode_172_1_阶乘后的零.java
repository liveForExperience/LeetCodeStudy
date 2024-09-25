package com.bottomlord.week_141;

/**
 * @author chen yue
 * @date 2022-03-25 09:19:35
 */
public class LeetCode_172_1_阶乘后的零 {
    public int trailingZeroes(int n) {
        int count5 = 0;
        for (int i = 2; i <= n; i++) {
            int num = i;
            while (num % 5 == 0) {
                num /= 5;
                count5++;
            }
        }

        return count5;
    }
}
