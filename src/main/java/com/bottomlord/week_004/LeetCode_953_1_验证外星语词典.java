package com.bottomlord.week_004;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/8/1 12:31
 */
public class LeetCode_953_1_验证外星语词典 {
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length == 0) {
            return true;
        }

        char[] orderC = order.toCharArray();
        int[] dict = new int[26];

        for (int i = 0; i < orderC.length; i++) {
            dict[orderC[i] - 'a'] = i;
        }

        List<List<Integer>> buffer = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            List<Integer> pre, cur;
            if (i - 1 < buffer.size()) {
                pre = buffer.get(i - 1);
            } else {
                pre = new ArrayList<>();
                for (char c : words[i - 1].toCharArray()) {
                    pre.add(dict[c - 'a']);
                }
                buffer.add(pre);
            }

            cur = new ArrayList<>();
            for (char c: words[i].toCharArray()) {
                cur.add(dict[c - 'a']);
            }

            int len = pre.size() <= cur.size() ? pre.size() : cur.size();
            boolean ok = false;
            for (int j = 0; j < len; j++) {
                if (pre.get(j) > cur.get(j)) {
                    return false;
                }
                if (pre.get(j).equals(cur.get(j))) {
                    continue;
                }
                ok = true;
                break;
            }

            if (!ok && pre.size() > cur.size()) {
                return false;
            }

            buffer.add(cur);
        }
        return true;
    }
}
