package com.bottomlord.week_200;

/**
 * @author chen yue
 * @date 2023-05-15 22:16:50
 */
public class LeetCode_2678_1_老人的数目 {
    public int countSeniors(String[] details) {
        int ans = 0;
        for (String detail : details) {
            char c = detail.charAt(11);
            if (c - '6' > 0 || (c == '6' && detail.charAt(12) != '0')) {
                ans++;
            }
        }
        return ans;
    }
}
