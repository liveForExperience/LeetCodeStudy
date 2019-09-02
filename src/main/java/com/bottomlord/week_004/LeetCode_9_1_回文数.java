package com.bottomlord.week_004;

/**
 * @author LiveForExperience
 * @date 2019/7/30 21:09
 */
public class LeetCode_9_1_回文数 {
    public boolean isPalindrome(int x) {
        String s = Integer.toString(x);
        char[] cs = s.toCharArray();
        int head =0, tail = cs.length - 1;
        while (head < tail) {
            if (cs[head++] != cs[tail--]) {
                return false;
            }
        }

        return true;
    }
}
