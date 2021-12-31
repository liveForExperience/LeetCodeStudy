package com.bottomlord.week_129;

/**
 * @author chen yue
 * @date 2021-12-31 08:46:18
 */
public class LeetCode_507_1_完美数 {
    public boolean checkPerfectNumber(int num) {
        int n = (int) Math.sqrt(num) + 1;
        int sum = 1;
        for (int i = 2; i < n; i++) {
            if (num % i == 0) {
                sum += i;
                sum += num / i == i ? 0 : num / i;
            }
        }

        return sum == n;
    }
}
