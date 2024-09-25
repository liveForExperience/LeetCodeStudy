package com.bottomlord.week_139;

/**
 * @author chen yue
 * @date 2022-03-08 08:56:40
 */
public class LeetCode_2055_1_蜡烛之间的盘子 {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int len = queries.length;
        int[] ans = new int[len];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = count(s, queries[i][0], queries[i][1]);
        }

        return ans;
    }

    private int count(String s, int left, int right) {
        int l = left - 1, r = right + 1;
        for (int i = left; i <= right; i++) {
            if (s.charAt(i) == '|') {
                l = i;
                break;
            }
        }

        for (int i = right; i >= left; i--) {
            if (s.charAt(i) == '|') {
                r = i;
                break;
            }
        }

        if (l == left - 1 || r == right + 1 || l >= r) {
            return 0;
        }

        int count = 0;
        for (int i = l; i <= r; i++) {
            if (s.charAt(i) == '*') {
                count++;
            }
        }

        return count;
    }
}
