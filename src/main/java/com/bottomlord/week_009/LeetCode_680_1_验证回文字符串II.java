package com.bottomlord.week_009;

public class LeetCode_680_1_验证回文字符串II {
    public boolean validPalindrome(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            int head = 0, tail = cs.length - 1;
            boolean flag = true;
            while (head < tail) {
                if (head == i) {
                    head++;
                }

                if (tail == i) {
                    tail--;
                }

                if (cs[head] != cs[tail]) {
                    flag = false;
                    break;
                }

                head++;
                tail--;
            }

            if (flag) {
                return true;
            }
        }

        return false;
    }
}
