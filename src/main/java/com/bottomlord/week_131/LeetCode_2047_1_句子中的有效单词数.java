package com.bottomlord.week_131;

/**
 * @author chen yue
 * @date 2022-01-11 21:12:43
 */
public class LeetCode_2047_1_句子中的有效单词数 {
    public int countValidWords(String sentence) {
        char[] cs = sentence.toCharArray();
        int count = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == ' ') {
                continue;
            }

            int start = i;
            while (i < cs.length && cs[i] != ' ') {
                i++;
            }
            int end = i - 1;

            if (check(cs, start, end)) {
                count++;
            }
        }

        return count;
    }

    private boolean check(char[] cs, int start, int end) {
        if (cs[start] == '-' || cs[end] == '-') {
            return false;
        }

        int count = 0, count2 = 0;

        for (int i = start; i <= end; i++) {
            char c = cs[i];
            if (c >= 'a' && c <= 'z') {
                continue;
            }

            if (c == '-') {
                count++;

                char pre = cs[i - 1], next = cs[i + 1];
                if (pre < 'a' || pre > 'z' || next < 'a' || next > 'z') {
                    return false;
                }

                if (count > 1) {
                    return false;
                }
                continue;
            }

            if (c == '!' || c == '.' || c == ',') {
                if (i != end) {
                    return false;
                }

                count2++;

                if(count2 > 1) {
                    return false;
                }

                continue;
            }

            return false;
        }

        return true;
    }
}
