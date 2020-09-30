package com.bottomlord.week_065;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/9/30 8:18
 */
public class LeetCode_293_1_翻转游戏 {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return list;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i) == '+') {
                list.add(s.substring(0, i) + "--" + s.substring(i + 2));
            }
        }

        return list;
    }
}
