package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/4 22:16
 */
public class Interview_1005_1_稀疏数组搜索 {
    public int findString(String[] words, String s) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if ("".equals(word)) {
                continue;
            }

            if (word.equals(s)) {
                return i;
            }
        }

        return -1;
    }
}
