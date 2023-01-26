package com.bottomlord.week_185;

/**
 * @author chen yue
 * @date 2023-01-26 15:49:29
 */
public class LeetCode_1663_1_具有给定数值的最小字符串 {
    public String getSmallestString(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (k > 0) {
            if (k == n) {
                for (int i = 0; i < n; i++) {
                    sb.insert(0, "a");
                }

                return sb.toString();
            }

            n--;
            int cur = k - n;

            if (cur > 26) {
                k -= 26;
                sb.insert(0, "z");
            } else {
                k -= cur;
                sb.insert(0, (char)('a' + cur - 1));
            }
        }

        return sb.toString();
    }
}
