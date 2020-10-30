package com.bottomlord.week_068;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/10/29 8:32
 */
public class LeetCode_320_1_列举单词的全部缩写 {
    public List<String> generateAbbreviations(String word) {
        List<String> ans = new ArrayList<>();
        backTrack(word, 0, 0, new StringBuilder(), ans);
        return ans;
    }

    private void backTrack(String word, int index, int num, StringBuilder sb, List<String> ans) {
        int len = sb.length();

        if (index == word.length()) {
            if (num != 0) {
                sb.append(num);
            }
            ans.add(sb.toString());
        } else {
            backTrack(word, index + 1, num + 1, sb, ans);

            if (num != 0) {
                sb.append(num);
            }

            sb.append(word.charAt(index));
            backTrack(word, index + 1, 0, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.setLength(len);
    }
}
