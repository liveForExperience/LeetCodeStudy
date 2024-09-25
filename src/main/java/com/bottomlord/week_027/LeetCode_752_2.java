package com.bottomlord.week_027;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ThinkPad
 * @date 2020/1/12 18:58
 */
public class LeetCode_752_2 {
    public int openLock(String[] deadends, String target) {
        if (isDead(deadends, "0000")) {
            return -1;
        }

        List<String> options = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            char[] cs = target.toCharArray();
            char c = cs[i];
            cs[i] = (char)((c - 48 + 1) % 10 + 48);
            String str1 = new String(cs);
            if (!isDead(deadends, str1)) {
                options.add(str1);
            }

            cs[i] = (char)((c - 48 + 9) % 10 + 48);
            String str2 = new String(cs);
            if (!isDead(deadends, str2)) {
                options.add(str2);
            }
        }

        if (options.size() == 0) {
            return -1;
        }

        int ans = 0;

        for (String option : options) {
            int cur = 1;
            char[] cs = option.toCharArray();

            for (int i = 0; i < 4; i++) {
                int num = cs[i] - '0';
                if (num > 5) {
                    cur += 10 - num;
                } else {
                    cur += num;
                }
            }

            if (ans == 0 || cur < ans) {
                ans = cur;
            }
        }

        return ans;
    }

    private boolean isDead(String[] deadends, String str) {
        for (String deadend : deadends) {
            if (Objects.equals(str, deadend)) {
                return true;
            }
        }

        return false;
    }
}