package com.bottomlord.week_005;

/**
 * @author LiveForExperience
 * @date 2019/8/6 13:22
 */
public class LeetCode_38_1_报数 {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        if (n == 2) {
            return "11";
        }

        String pre = "11";
        while (n-- > 1) {
            char[] cs = pre.toCharArray();

            StringBuilder sb = new StringBuilder();
            char preC = cs[0];
            int len = 1;

            for (int i = 1; i < cs.length; i++) {
                if (preC == cs[i]) {
                    len++;
                } else {
                    sb.append(len).append(preC);
                    preC = cs[i];
                    len = 1;
                }
            }

            sb.append(len).append(preC);
            pre = sb.toString();
        }
        return pre;
    }
}
