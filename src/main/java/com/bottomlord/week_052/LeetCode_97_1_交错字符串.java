package com.bottomlord.week_052;

/**
 * @author ChenYue
 * @date 2020/7/5 18:07
 */
public class LeetCode_97_1_交错字符串 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }

        return backTrack(0, s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray());
    }

    private boolean backTrack(int i1, char[] cs1, int i2, char[] cs2, int i3, char[] cs3) {
        if (i3 == cs3.length) {
            return true;
        }

        boolean flag = false;
        if (i1 != cs1.length && cs1[i1] == cs3[i3]) {
            flag = backTrack(i1 + 1, cs1, i2, cs2, i3 + 1, cs3);
        }

        if (flag) {
            return true;
        }

        if (i2 != cs2.length && cs2[i2] == cs3[i3]) {
            return backTrack(i1, cs1, i2 + 1, cs2, i3 + 1, cs3);
        }

        return false;
    }
}
