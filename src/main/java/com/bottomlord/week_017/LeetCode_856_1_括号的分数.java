package com.bottomlord.week_017;

public class LeetCode_856_1_括号的分数 {
    public int scoreOfParentheses(String S) {
        return divideAndConquer(S.toCharArray(), 0, S.length());
    }

    private int divideAndConquer(char[] cs, int start, int end) {
        int balance = 0, ans = 0;
        for (int i = start; i < end; i++) {
            balance += cs[i] == '(' ? 1 : -1;
            if (balance == 0) {
                if (i - start == 1) {
                    ans++;
                } else {
                    ans += 2 * divideAndConquer(cs, start + 1, i);
                }
                start = i + 1;
            }
        }
        return ans;
    }
}
