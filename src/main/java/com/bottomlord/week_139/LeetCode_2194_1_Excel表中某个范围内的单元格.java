package com.bottomlord.week_139;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-03-08 21:26:04
 */
public class LeetCode_2194_1_Excel表中某个范围内的单元格 {
    public List<String> cellsInRange(String s) {
        List<String> ans = new ArrayList<>();
        int row1 = s.charAt(0) - 'A',
            col1 = s.charAt(1) - '0',
            row2 = s.charAt(3) - 'A',
            col2 = s.charAt(4) - '0';

        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                ans.add(String.valueOf((char)(i + 'A')) + j);
            }
        }

        return ans;
    }
}
