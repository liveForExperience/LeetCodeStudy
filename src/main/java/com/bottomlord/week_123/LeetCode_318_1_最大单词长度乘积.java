package com.bottomlord.week_123;

/**
 * @author chen yue
 * @date 2021-11-17 08:42:22
 */
public class LeetCode_318_1_最大单词长度乘积 {
    public int maxProduct(String[] words) {
        int len = 0;
        for (int i = 0; i < words.length; i++) {
            boolean[] bucket = new boolean[26];
            char[] cs = words[i].toCharArray();
            for (char c : cs) {
                bucket[c - 'a'] = true;
            }

            int il = words[i].length();

            for (int j = i + 1; j < words.length; j++) {
                if (il * words[j].length() <= len) {
                    continue;
                }

                char[] jcs = words[j].toCharArray();
                boolean flag = true;
                for (char jc : jcs) {
                    if (bucket[jc - 'a']) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    len = il * words[j].length();
                }
            }
        }

        return len;
    }
}
