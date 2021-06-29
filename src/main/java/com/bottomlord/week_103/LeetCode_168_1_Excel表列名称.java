package com.bottomlord.week_103;

/**
 * @author ChenYue
 * @date 2021/6/29 8:15
 */
public class LeetCode_168_1_Excel表列名称 {
    public String convertToTitle(int columnNumber) {
        char[] cs = new char[26];
        cs[0] = 'Z';
        cs[1] = 'A';
        for (int i = 2; i < 26; i++) {
            cs[i] = (char)(cs[i - 1] + 1);
        }

        StringBuilder sb = new StringBuilder();
        while (columnNumber != 0) {
            int digit = columnNumber % 26;
            if (digit == 0) {
                columnNumber--;
            }
            sb.insert(0, cs[digit]);
            columnNumber /= 26;
        }

        return sb.toString();
    }
}
