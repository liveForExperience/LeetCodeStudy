package com.bottomlord.week_017;

public class LeetCode_856_3 {
    public int scoreOfParentheses(String S) {
        int balance = 0, ans = 0;
        char[] cs = S.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '(') {
                balance++;
            } else {
                balance--;
                if (cs[i - 1] == '(') {
                    ans += 1 << balance;
                }
            }
        }
        return ans;
    }
}