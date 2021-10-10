package com.bottomlord.week_117;

/**
 * @author chen yue
 * @date 2021-10-10 11:17:26
 */
public class LeetCode_1790_2 {
    public boolean areAlmostEqual(String s1, String s2) {
        int x1 = -1, x2 = -1, count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;

                if (count == 1) {
                    x1 = i;
                }

                if (count == 2) {
                    x2 = i;
                }
            }
        }

        if (count == 0) {
            return true;
        }

        return s1.charAt(x1) == s2.charAt(x2) && s1.charAt(x2) == s2.charAt(x1);
    }
}
