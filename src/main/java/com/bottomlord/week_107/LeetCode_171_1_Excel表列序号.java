package com.bottomlord.week_107;

/**
 * @author ChenYue
 * @date 2021/7/30 9:19
 */
public class LeetCode_171_1_Excel表列序号 {
    public int titleToNumber(String columnTitle) {
        int sum = 0;
        char[] cs = columnTitle.toCharArray();
        for (char c : cs) {
            sum = sum * 26 + (c - 'A' + 1);
        }
        return sum;
    }
}
