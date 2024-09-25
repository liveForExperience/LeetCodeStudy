package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-21 20:32:09
 */
public class LeetCode_58_1_最后一个单词的长度 {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int count = 0;
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                break;
            }
            count++;
        }

        return count;
    }
}
