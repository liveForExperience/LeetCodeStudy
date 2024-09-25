package com.bottomlord.week_128;

/**
 * @author chen yue
 * @date 2021-12-25 18:54:58
 */
public class LeetCode_1952_1_三除数 {
    public boolean isThree(int n) {
        int len = (int) Math.sqrt(n) + 1, count = 0;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                if (n / i != i) {
                    return false;
                }

                count++;

                if (count > 1) {
                    return false;
                }
            }
        }

        return count == 1;
    }
}
