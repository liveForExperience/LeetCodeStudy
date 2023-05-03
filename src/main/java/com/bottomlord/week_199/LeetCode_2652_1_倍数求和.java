package com.bottomlord.week_199;

/**
 * @author chen yue
 * @date 2023-05-03 13:39:46
 */
public class LeetCode_2652_1_倍数求和 {
    public int sumOfMultiples(int n) {
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
