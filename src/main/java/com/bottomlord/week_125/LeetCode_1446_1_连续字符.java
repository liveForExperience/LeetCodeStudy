package com.bottomlord.week_125;

/**
 * @author chen yue
 * @date 2021-12-01 08:48:41
 */
public class LeetCode_1446_1_连续字符 {
    public int maxPower(String s) {
        char[] cs = s.toCharArray();
        int max = 0;
        for (int i = 0; i < cs.length;) {
            char c = cs[i];
            int count = 0;
            while (i < cs.length && c == cs[i]) {
                count++;
                i++;
            }

            max = Math.max(count, max);
        }

        return max;
    }
}
