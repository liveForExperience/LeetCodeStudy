package com.bottomlord.week_4;

/**
 * @author LiveForExperience
 * @date 2019/7/30 21:28
 */
public class LeetCode_9_3 {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        if (x == 0) {
            return true;
        }

        if (x % 10 == 0) {
            return false;
        }

        int reserve = 0, cur = x;
        while (cur > reserve) {
            reserve = 10 * reserve + cur % 10;
            cur = cur / 10;
        }
        return (reserve == cur || cur == reserve / 10);
    }
}
