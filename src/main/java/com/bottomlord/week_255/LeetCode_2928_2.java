package com.bottomlord.week_255;

/**
 * @author chen yue
 * @date 2024-06-01 14:42:33
 */
public class LeetCode_2928_2 {
    public int distributeCandies(int n, int limit) {
        int sum = 0;
        for (int one = 0; one <= limit; one++) {
            for (int two = 0; two <= limit; two++) {
                if (n - one - two >= 0 &&
                    n - one - two <= limit) {
                    sum++;
                }
            }
        }

        return sum;
    }
}
