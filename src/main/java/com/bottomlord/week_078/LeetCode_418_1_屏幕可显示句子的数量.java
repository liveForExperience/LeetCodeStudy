package com.bottomlord.week_078;

import java.util.StringJoiner;

/**
 * @author ChenYue
 * @date 2021/1/4 9:03
 */
public class LeetCode_418_1_屏幕可显示句子的数量 {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int count = 0, len = String.join(" ", sentence).length() + 1, index = 0;
        for (int i = 0; i < rows; i++) {
            int leftCol = cols;
            while (leftCol > 0) {
                if (sentence[index].length() <= leftCol) {
                    leftCol -= sentence[index].length();
                } else {
                    break;
                }

                index++;
                if (leftCol != 0) {
                    leftCol--;
                }

                if (index == sentence.length) {
                    count += (1 + leftCol / len);
                    leftCol = leftCol % len;
                    index = 0;
                }
            }
        }
        return count;
    }
}
