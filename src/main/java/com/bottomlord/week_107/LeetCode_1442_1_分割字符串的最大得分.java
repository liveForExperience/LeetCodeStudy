package com.bottomlord.week_107;

public class LeetCode_1442_1_分割字符串的最大得分 {
    public int maxScore(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();

        int[] zero = new int[n], one = new int[n];
        int max = Integer.MIN_VALUE;

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '0') {
                count++;
            }
            zero[i] = count;
        }

        count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (cs[i] == '1') {
                count++;
            }
            one[i] = count;
        }

        for (int i = 1; i < n; i++) {
            max = Math.max(max, zero[i - 1] + one[i]);
        }

        return max;
    }
}
