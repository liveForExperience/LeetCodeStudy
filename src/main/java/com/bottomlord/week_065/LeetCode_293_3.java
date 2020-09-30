package com.bottomlord.week_065;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/9/30 8:40
 */
public class LeetCode_293_3 {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new ArrayList<>();
        if (s.length() < 2) {
            return list;
        }

        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length - 1; i++) {
            if (cs[i] == '+' && cs[i + 1] == '+') {
                cs[i] = '-';
                cs[i + 1] = '-';
                list.add(new String(cs));
                cs[i] = '+';
                cs[i + 1] = '+';
            }
        }

        return list;
    }
}