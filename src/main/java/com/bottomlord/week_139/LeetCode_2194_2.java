package com.bottomlord.week_139;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-03-08 21:33:43
 */
public class LeetCode_2194_2 {
    public List<String> cellsInRange(String s) {
        List<String> ans = new ArrayList<>();
        for (int i = s.charAt(0); i <= s.charAt(3); i++) {
            for (int j = s.charAt(1); j <= s.charAt(4); j++) {
                ans.add(new String(new char[]{(char)i, (char)j}));
            }
        }
        return ans;
    }
}
