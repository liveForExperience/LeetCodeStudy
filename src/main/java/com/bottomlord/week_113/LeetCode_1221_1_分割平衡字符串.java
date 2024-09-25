package com.bottomlord.week_113;

/**
 * @author chen yue
 * @date 2021-09-07 08:16:58
 */
public class LeetCode_1221_1_分割平衡字符串 {
    public int balancedStringSplit(String s) {
        int l = 0, r = 0, ans = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                l++;
            } else if (c == 'R') {
                r++;
            }

            if (l == r) {
                ans++;
                l = 0;
                r = 0;
            }
        }

        return ans;
    }
}
