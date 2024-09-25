package com.bottomlord.week_030;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/1/29 13:51
 */
public class LeetCode_6_1_Z字形变换 {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            list.add(new StringBuilder());
        }

        boolean down = false;
        int row = 0;
        for (char c : s.toCharArray()) {
            list.get(row).append(c);
            if (row == 0 || row == numRows - 1) {
                down = !down;
            }
            row = down ? row + 1 : row - 1;
        }

        return String.join("", list);
    }
}
