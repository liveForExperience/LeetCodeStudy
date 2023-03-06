package com.bottomlord.week_191;

/**
 * @author chen yue
 * @date 2023-03-06 17:30:16
 */
public class LeetCode_1653_1_使字符串平衡的最少删除次数 {
    public int minimumDeletions(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, preB = 0, sufA = 0;
        int[] preBs = new int[n], sufAs = new int[n];
        for (int i = 0; i < n; i++) {
            preBs[i] = preB + (cs[i] == 'b' ? 1 : 0);
            preB = preBs[i];
        }

        for (int i = n - 1; i >= 0; i--) {
            sufAs[i] = sufA + (cs[i] == 'a' ? 1 : 0);
            sufA = sufAs[i];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cs.length; i++) {
            ans = Math.min(ans, preBs[i] + sufAs[i] - 1);
        }
        return ans;
    }
}
