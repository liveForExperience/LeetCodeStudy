package com.bottomlord.week_025;

/**
 * @author ThinkPad
 * @date 2019/12/29 10:33
 */
public class LeetCode_481_1_神奇字符串 {
    public int magicalString(int n) {
        if (n == 0) {
            return 0;
        }

        if (n <= 3) {
            return 1;
        }

        StringBuilder s = new StringBuilder("122");
        int p1 = 2, p2 = 2, ans = 1;
        while (s.length() < n) {
            char c = s.charAt(p1) == '1' ? '2' : '1';
            int time = s.charAt(p2) - '0';

            for (int i = 0; i < time; i++) {
                s.append(c);
                if (c == '1') {
                    ans++;
                }
                if (s.length() == n) {
                    return ans;
                }
            }

            p1 = s.length() - 1;
            p2++;
        }

        return ans;
    }
}
