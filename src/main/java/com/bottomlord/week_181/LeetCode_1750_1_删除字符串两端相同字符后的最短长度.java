package com.bottomlord.week_181;

/**
 * @author chen yue
 * @date 2022-12-28 10:59:36
 */
public class LeetCode_1750_1_删除字符串两端相同字符后的最短长度 {
    public int minimumLength(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            char lc = s.charAt(left),
                 rc = s.charAt(right);

            if (lc != rc) {
                return right - left + 1;
            }

            while (left < right && s.charAt(left) == lc) {
                left++;
            }

            while (left < right && s.charAt(right) == rc) {
                right--;
            }
        }

        return right - left + 1;
    }
}
