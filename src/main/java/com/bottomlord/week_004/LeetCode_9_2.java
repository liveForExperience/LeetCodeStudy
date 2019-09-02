package com.bottomlord.week_004;

/**
 * @author LiveForExperience
 * @date 2019/7/30 21:14
 */
public class LeetCode_9_2 {
    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        if (x == x % 10) {
            return true;
        }

        int reserve = 0, cur = x;
        while (cur >= 10) {
            int digit = cur % 10;
            reserve = reserve * 10 + digit;
            cur /= 10;
            if (reserve == cur || reserve == cur / 10) {
                return true;
            }
        }
        return false;
    }
}
