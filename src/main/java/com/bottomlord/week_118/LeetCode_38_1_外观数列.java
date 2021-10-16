package com.bottomlord.week_118;

/**
 * @author chen yue
 * @date 2021-10-15 08:43:21
 */
public class LeetCode_38_1_外观数列 {
    public String countAndSay(int n) {
        StringBuilder ans = new StringBuilder("1");
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int len = ans.length(), count = 0;
            char start = ans.charAt(0);
            for (int j = 0; j < len; j++) {
                if (ans.charAt(j) == start) {
                    count++;
                } else {
                    sb.append(count).append(start);
                    start = ans.charAt(j);
                    count = 1;
                }
            }

            sb.append(count).append(start);
            ans = sb;
        }

        return ans.toString();
    }
}
