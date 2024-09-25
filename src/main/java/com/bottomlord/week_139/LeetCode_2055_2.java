package com.bottomlord.week_139;

/**
 * @author chen yue
 * @date 2022-03-08 13:41:15
 */
public class LeetCode_2055_2 {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int len = queries.length;
        int[] left = new int[len],
              right = new int[len],
              sums = new int[len + 1];

        char[] cs = s.toCharArray();
        int pre = -1, count = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '|') {
                pre = i;
            }

            right[i] = pre;

            if (cs[i] == '*') {
                count++;
            }

            sums[i + 1] = count;
        }

        pre = cs.length;
        for (int i = cs.length - 1; i >= 0; i--) {
            if (cs[i] == '|') {
                pre = i;
            }

            left[i] = pre;
        }

        int[] ans = new int[len];
        for (int i = 0; i < queries.length; i++) {
            int l = left[queries[i][0]], r = right[queries[i][1]];
            if (l >= r) {
                ans[i] = 0;
            } else {
                ans[i] = sums[r] - sums[l];
            }
        }

        return ans;
    }
}