package com.bottomlord.week_132;

/**
 * @author chen yue
 * @date 2022-01-22 14:25:16
 */
public class LeetCode_1332_1_删除回文子序列 {
    public int removePalindromeSub(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return 2;
            }
        }
        return 1;
    }
}
