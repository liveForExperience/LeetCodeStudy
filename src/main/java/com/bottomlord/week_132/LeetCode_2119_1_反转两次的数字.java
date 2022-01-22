package com.bottomlord.week_132;

/**
 * @author chen yue
 * @date 2022-01-22 21:46:14
 */
public class LeetCode_2119_1_反转两次的数字 {
    public boolean isSameAfterReversals(int num) {
        return num == reverse(reverse(num));
    }

    private int reverse(int num) {
        int ans = 0;
        while (num > 0) {
            int cur = num % 10;
            ans = ans * 10 + cur;
            num /= 10;
        }
        return ans;
    }
}
