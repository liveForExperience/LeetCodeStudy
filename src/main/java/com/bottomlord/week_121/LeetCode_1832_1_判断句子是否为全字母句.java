package com.bottomlord.week_121;

/**
 * @author chen yue
 * @date 2021-11-06 14:32:13
 */
public class LeetCode_1832_1_判断句子是否为全字母句 {
    public boolean checkIfPangram(String sentence) {
        boolean[] bucket = new boolean[26];
        for (char c : sentence.toCharArray()) {
            bucket[c - 'a'] = true;
        }

        for (boolean b : bucket) {
            if (!b) {
                return false;
            }
        }

        return true;
    }
}
