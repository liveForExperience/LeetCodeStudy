package com.bottomlord.week_092;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/4/16 8:38
 */
public class LeetCdoe_87_1_扰乱字符串 {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() == 1) {
            return Objects.equals(s1, s2);
        }

        for (int i = 1; i < s1.length(); i++) {
            List<String> lefts = dfs(s1.substring(0, i)),
                         rights = dfs(s1.substring(i));

            for (String left : lefts) {
                for (String right : rights) {
                    if (Objects.equals(left + right, s2) ||
                        Objects.equals(right + left, s2)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private List<String> dfs(String s1) {
        if (s1.length() == 1) {
            return Collections.singletonList(s1);
        }

        List<String> ans = new ArrayList<>();
        for (int i = 1; i < s1.length(); i++) {
            List<String> lefts = dfs(s1.substring(0, i)),
                         rights = dfs(s1.substring(i));

            for (String left : lefts) {
                for (String right : rights) {
                    ans.add(left + right);
                    ans.add(right + left);
                }
            }
        }

        return ans;
    }
}
