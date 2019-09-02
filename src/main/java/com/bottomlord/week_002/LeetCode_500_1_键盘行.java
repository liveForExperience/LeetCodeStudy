package com.bottomlord.week_002;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/16 12:45
 */
public class LeetCode_500_1_键盘行 {
    public String[] findWords(String[] words) {
        int[] board = new int[]{2,3,3,2,1,2,2,2,1,2,2,2,3,3,1,1,1,1,2,1,1,3,1,3,1,3};
        List<String> list = new ArrayList<>();

        for (String word: words) {
            char[] cs = word.toCharArray();
            int line;
            boolean flag = true;

            if (cs[0] < 'a') {
                line = board[cs[0] - 'A'];
            } else {
                line = board[cs[0] - 'a'];
            }
            for (int i = 1; i < cs.length; i++) {
                int cur;
                if (cs[i] < 'a') {
                    cur = board[cs[i] - 'A'];
                } else {
                    cur = board[cs[i] - 'a'];
                }

                if (cur != line) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                list.add(word);
            }
        }

        return list.toArray(new String[0]);
    }
}
