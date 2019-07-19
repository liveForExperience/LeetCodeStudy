package com.bottomlord.week_2;

/**
 * @author LiveForExperience
 * @date 2019/7/19 12:32
 */
public class LeetCode_171_1_Excel表列序号 {
    public int titleToNumber(String s) {
        int ans = 0;
        char[] cs = s.toCharArray();

        for (int i = 0, bit = cs.length - 1; i < cs.length; i++, bit--) {
            ans += Math.pow(26, i) * (cs[i] - 'A');
        }

        return ans;
    }
}
