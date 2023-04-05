package com.bottomlord.week_195;

/**
 * @author chen yue
 * @date 2023-04-05 14:17:34
 */
public class LeetCode_2609_1_最长平衡字符串 {
    public int findTheLongestBalancedSubstring(String s) {
        int zero = 0, one = 0, ans = 0, index = 0, n = s.length();
        while (index < n) {
            while (index < n && s.charAt(index) == '0') {
                zero++;
                index++;
            }

            while (index < n && s.charAt(index) == '1') {
                one++;
                index++;
            }

            ans = Math.max(ans, Math.min(zero, one));

            zero = 0;
            one = 0;
        }

        return ans;
    }
}
