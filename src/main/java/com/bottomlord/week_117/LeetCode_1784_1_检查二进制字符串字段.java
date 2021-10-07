package com.bottomlord.week_117;

/**
 * @author chen yue
 * @date 2021-10-07 12:54:46
 */
public class LeetCode_1784_1_检查二进制字符串字段 {
    public boolean checkOnesSegment(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            }

            while (s.charAt(i) == '1') {
                i++;
            }
        }

        return count == 1;
    }
}
