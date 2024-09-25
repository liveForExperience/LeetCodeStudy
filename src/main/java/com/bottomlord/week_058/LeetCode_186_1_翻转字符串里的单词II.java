package com.bottomlord.week_058;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/8/10 8:34
 */
public class LeetCode_186_1_翻转字符串里的单词II {
    public void reverseWords(char[] s) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s) {
            if (c != ' ') {
                sb.append(c);
            } else {
                list.add(sb.toString());
                sb = new StringBuilder();
            }
        }

        if (sb.length() != 0) {
            list.add(sb.toString());
        }

        int head = 0, tail = list.size() - 1;
        while (head < tail) {
            String tmp = list.get(tail);
            list.set(tail, list.get(head));
            list.set(head, tmp);

            head++;
            tail--;
        }

        sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
        }

        String str = sb.toString();
        for (int i = 0; i < s.length; i++) {
            s[i] = str.charAt(i);
        }
    }
}
