package com.bottomlord.week_103;

/**
 * @author ChenYue
 * @date 2021/6/29 8:42
 */
public class LeetCode_168_2 {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber != 0) {
            columnNumber--;
            sb.insert(0, (char)(columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }

        return sb.toString();
    }
}
