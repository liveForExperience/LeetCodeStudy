package com.bottomlord.week_009;

public class LeetCode_58_1_最后一个单词的长度 {
    public int lengthOfLastWord(String s) {
        char[] cs = s.toCharArray();
        int start = -1, end = -1;
        boolean findEnd = false, findStart = false;
        for (int i = cs.length - 1; i >= 0; i--) {
            if (cs[i] != ' ') {
                if (i == cs.length - 1) {
                    end = i + 1;
                    findEnd = true;
                } else if (cs[i + 1] == ' ') {
                    end = i + 1;
                    findEnd = true;
                }

                if (i == 0) {
                    start = i;
                    findStart = true;
                } else if (cs[i - 1] == ' ') {
                    start = i;
                    findStart = true;
                }
            }

            if (findStart && findEnd) {
                break;
            }
        }

        return end - start;
    }
}
