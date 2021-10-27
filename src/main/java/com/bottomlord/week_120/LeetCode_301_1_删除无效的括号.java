package com.bottomlord.week_120;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-10-27 08:38:02
 */
public class LeetCode_301_1_删除无效的括号 {
    public List<String> removeInvalidParentheses(String s) {
        int lr = 0, rr = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                lr++;
            } else if (s.charAt(i) == ')'){
                if (lr == 0) {
                    rr++;
                } else {
                    lr--;
                }
            }
        }

        List<String> ans = new ArrayList<>();
        dfs(s, 0, 0, 0, lr, rr, ans);
        return ans;
    }

    private void dfs(String s, int index, int lc, int rc, int lr, int rr, List<String> ans) {
        if (lr == 0 && rr == 0) {
            if (isValid(s)) {
                ans.add(s);
            }

            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (i != index && s.charAt(i) == s.charAt(i - 1)) {
                if (s.charAt(i) == '(') {
                    lc++;
                }

                if (s.charAt(i) == ')') {
                    rc++;
                }

                continue;
            }

            if (lr + rr > s.length() - i) {
                return;
            }

            if (lr > 0 && s.charAt(i) == '(') {
                dfs(s.substring(0, i) + s.substring(i + 1), i, lc, rc, lr - 1, rr, ans);
            }

            if (rr > 0 && s.charAt(i) == ')') {
                dfs(s.substring(0, i) + s.substring(i + 1), i, lc, rc, lr, rr - 1, ans);
            }

            if (s.charAt(i) == '(') {
                lc++;
            }

            if (s.charAt(i) == ')') {
                rc++;
            }

            if (lc < rc) {
                return;
            }
        }
    }

    private boolean isValid(String s) {
        int lc = 0, rc = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                lc++;
            } else if (s.charAt(i) == ')'){
                rc++;
            }

            if (lc < rc) {
                return false;
            }
        }

        return lc == rc;
    }
}
