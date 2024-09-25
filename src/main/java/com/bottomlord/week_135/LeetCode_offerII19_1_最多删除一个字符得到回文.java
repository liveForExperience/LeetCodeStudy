package com.bottomlord.week_135;

/**
 * @author chen yue
 * @date 2022-02-11 08:53:49
 */
public class LeetCode_offerII19_1_最多删除一个字符得到回文 {
    public boolean validPalindrome(String s) {
        int n = s.length();
        return valid(s, 0, n - 1, 0) ||
               valid(s, 0, n - 2, 1) ||
               valid(s, 1, n - 1, 1);
    }

    private boolean valid(String s, int head, int tail, int count) {
        if (count > 1) {
            return false;
        }

        while (head < tail) {
            if (s.charAt(head) != s.charAt(tail)) {
                return valid(s, head + 1, tail, count + 1) || valid(s, head, tail - 1, count + 1);
            }

            head++;
            tail--;
        }

        return true;
    }
}
