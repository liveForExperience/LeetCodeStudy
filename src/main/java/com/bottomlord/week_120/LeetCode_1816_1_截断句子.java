package com.bottomlord.week_120;

/**
 * @author chen yue
 * @date 2021-10-31 21:04:06
 */
public class LeetCode_1816_1_截断句子 {
    public String truncateSentence(String s, int k) {
        String[] ss = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(ss[i]);
            if (i != k - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
