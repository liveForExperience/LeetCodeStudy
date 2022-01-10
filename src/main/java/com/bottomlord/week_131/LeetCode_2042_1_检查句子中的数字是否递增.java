package com.bottomlord.week_131;

/**
 * @author chen yue
 * @date 2022-01-10 21:10:28
 */
public class LeetCode_2042_1_检查句子中的数字是否递增 {
    public boolean areNumbersAscending(String s) {
        int pre = 0;
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (!isDigit(cs[i])) {
                continue;
            }

            int num = 0;
            while (i < cs.length && isDigit(cs[i])) {
                num = num * 10 + (cs[i] - '0');
                i++;
            }

            if (num <= pre) {
                return false;
            }
        }

        return true;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
