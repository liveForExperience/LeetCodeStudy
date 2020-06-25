package com.bottomlord.week_051;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/6/23 8:53
 */
public class LeetCode_68_1_文本左右对齐 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int wordNum = words.length, maxRowLen = maxWidth + 1;
        List<String> ans = new ArrayList<>();

        int rowLen = maxRowLen;
        List<String> wordList = new ArrayList<>();
        for (int i = 0; i < wordNum; i++) {
            String word = words[i];
            if (rowLen - word.length() - 1 >= 0) {
                wordList.add(word);
                if (i == wordNum - 1) {
                    ans.add(fillSpecialRow(wordList, maxWidth));
                    break;
                }
                rowLen -= (word.length() + 1);
            } else {
                if (wordList.size() == 1) {
                    ans.add(fillSpecialRow(wordList, maxWidth));
                } else {
                    ans.add(fillRow(wordList, rowLen, maxWidth));
                }
                wordList.clear();
                rowLen = maxRowLen;
                i--;
            }
        }

        return ans;
    }

    private String fillRow(List<String> wordList, int leftLen, int maxWidth) {
        StringBuilder sb = new StringBuilder();

        int wordsNum = wordList.size();
        int gaps = wordsNum - 1;
        int avgExtraLen = leftLen / gaps;
        int extraNum = leftLen % gaps;

        for (int i = 0; i < wordList.size(); i++) {
            sb.append(wordList.get(i));
            if (i < wordsNum - 1) {
                sb.append(' ');
                for (int j = 0; j < avgExtraLen; j++) {
                    sb.append(' ');
                }
            }
            if (extraNum > 0) {
                sb.append(' ');
                extraNum--;
            }
        }

        return sb.toString();
    }

    private String fillSpecialRow(List<String> words, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            maxWidth -= word.length();
            if (maxWidth == 0) {
                break;
            }

            maxWidth--;
            if (maxWidth >= 0) {
                sb.append(' ');
            }
        }

        while (maxWidth-- > 0) {
            sb.append(' ');
        }

        return sb.toString();
    }
}
