package com.bottomlord.week_003;

/**
 * @author LiveForExperience
 * @date 2019/7/23 14:30
 */
public class LeetCode_806_1_写字符串需要的行数 {
    public int[] numberOfLines(int[] widths, String S) {
        int[] ans = new int[2];
        char[] cs = S.toCharArray();

        int line = 0;
        int word = 0;
        for (char i = 0; i < cs.length; i++) {
            int num = word + widths[cs[i] - 'a'];
            if (num == 100) {
                line++;
                word = 0;
                continue;
            }

            if (num > 100) {
                line++;
                word = widths[cs[i] - 'a'];
                continue;
            }

            word = num;
        }

        ans[0] = line + 1;
        ans[1] = word;
        return ans;
    }
}
