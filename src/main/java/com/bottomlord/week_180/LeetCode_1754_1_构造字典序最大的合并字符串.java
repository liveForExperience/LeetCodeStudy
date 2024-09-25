package com.bottomlord.week_180;

/**
 * @author chen yue
 * @date 2022-12-24 06:40:55
 */
public class LeetCode_1754_1_构造字典序最大的合并字符串 {
    public String largestMerge(String word1, String word2) {
        int i = 0, j = 0, n1 = word1.length(), n2 = word2.length();
        StringBuilder sb = new StringBuilder();
        while (i < n1 || j < n2) {
            if (judge(word1, i, n1, word2, j, n2)) {
                sb.append(word1.charAt(i++));
            } else {
                sb.append(word2.charAt(j++));
            }
        }

        return sb.toString();
    }

    private boolean judge(String word1, int i, int n1, String word2, int j, int n2) {
        if (i >= n1) {
            return false;
        } else if (j >= n2) {
            return true;
        } else {
            return word1.substring(i).compareTo(word2.substring(j)) >= 0;
        }
    }
}
