package com.bottomlord.contest_20220619;

/**
 * @author chen yue
 * @date 2022-06-19 10:17:45
 */
public class Contest_2_1_个位数为k的整数之和 {
    public int minimumNumbers(int num, int k) {
        int count = 0;

        int x = k == 0 ? 10 : k;
        while (num > 0) {
            if (num % 10 == k) {
                return count;
            }

            num -= x;
            if (num < 0) {
                return -1;
            }

            count++;
        }

        return count;
    }
}
