package com.bottomlord.week_079;

import java.util.List;

/**
 * @author ChenYue
 * @date 2021/1/15 17:11
 */
public class LeetCode_422_1_有效的单词方块 {
    public boolean validWordSquare(List<String> words) {
        int row = words.size();
        if (row == 0) {
            return true;
        }

        for (int i = 0; i < row; i++) {
            String word = words.get(i);
            for (int j = 0; j < word.length(); j++) {
                if (j >= words.size()) {
                    return false;
                }

                if (i >= words.get(j).length()) {
                    return false;
                }

                if (words.get(j).charAt(i) != word.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }
}
