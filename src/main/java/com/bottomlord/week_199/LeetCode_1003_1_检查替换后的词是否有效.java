package com.bottomlord.week_199;

/**
 * @author chen yue
 * @date 2023-05-03 11:26:25
 */
public class LeetCode_1003_1_检查替换后的词是否有效 {
    public boolean isValid(String s) {
        int i;
        while ((i = s.indexOf("abc")) != -1) {
            s = s.substring(0, i) + s.substring(i + 3);
        }

        return s.length() == 0;
    }
}
