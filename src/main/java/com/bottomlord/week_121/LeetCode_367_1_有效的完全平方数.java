package com.bottomlord.week_121;

/**
 * @author chen yue
 * @date 2021-11-04 08:59:09
 */
public class LeetCode_367_1_有效的完全平方数 {
    public boolean isPerfectSquare(int num) {
        if (num == Integer.MAX_VALUE) {
            return false;
        }

        for (int i = 1; i <= num; i++) {
            if (i * i > num) {
                break;
            }

            if (i * i == num) {
                return true;
            }
        }

        return false;
    }
}
