package com.bottomlord.week_065;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/9/30 8:28
 */
public class LeetCode_293_2 {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return list;
        }

        int i = 0;
        while (i < s.length() - 1) {
            if (s.charAt(i) == '-') {
                i++;
                continue;
            }

            if (s.charAt(i + 1) == '-') {
                i += 2;
            }

            list.add(s.substring(0, i) + "--" + s.substring(i + 2));
            i++;
        }

        return list;
    }
}
