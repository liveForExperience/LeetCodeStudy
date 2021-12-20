package com.bottomlord.week_128;

/**
 * @author chen yue
 * @date 2021-12-20 09:10:06
 */
public class LeetCode_1933_1_判断字符串是否可分解为值均等的子串 {
    public boolean isDecomposable(String s) {
        boolean find2 = false;
        for (int i = 0; i < s.length(); i++) {
            int count = 1;
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                count++;
                i++;
            }

            count %= 3;

            if (count == 1) {
                return false;
            }

            if (count == 2) {
                if (find2) {
                    return false;
                }

                find2 = true;
            }
        }

        return find2;
    }
}
