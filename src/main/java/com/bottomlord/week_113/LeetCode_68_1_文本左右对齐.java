package com.bottomlord.week_113;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-09-09 08:19:34
 */
public class LeetCode_68_1_文本左右对齐 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int wordNum = words.length, rowLen = maxWidth + 1, curRowLen = rowLen;
        List<String> curRowWords = new ArrayList<>(), ans = new ArrayList<>();
        for (int i = 0; i < wordNum; i++) {
            String word = words[i];

            if (curRowLen - word.length() - 1 >= 0) {
                curRowWords.add(word);
                if (i == wordNum - 1) {
                    ans.add(fillSpecialRow(curRowWords, maxWidth));
                }
                curRowLen -= (word.length() + 1);
            } else {
                if (curRowWords.size() == 1) {
                    ans.add(fillSpecialRow(curRowWords, maxWidth));
                } else {
                    ans.add(fillRow(curRowWords, maxWidth, curRowLen));
                }

                curRowWords.clear();
                curRowLen = rowLen;
                i--;
            }
        }

        return ans;
    }

    private String fillRow(List<String> words, int len, int leftLen) {
        int wordNum = words.size(), gaps = wordNum - 1,
            extraGapLen = leftLen / gaps, extraLen = leftLen % gaps;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordNum; i++) {
            sb.append(words.get(i));
            if (i != wordNum - 1) {
                sb.append(" ");

                for (int j = 0; j < extraGapLen; j++) {
                    sb.append(" ");
                }
            }

            if (extraLen > 0) {
                sb.append(" ");
                extraLen--;
            }
        }

        return sb.toString();
    }

    private String fillSpecialRow(List<String> words, int len) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            if (sb.length() < len) {
                sb.append(" ");
            }
        }

        while (sb.length() < len) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
