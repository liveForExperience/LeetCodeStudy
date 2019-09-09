package com.bottomlord.week_010;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_22_2 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            return ans;
        }

        char[] cs = new char[2 * n];
        cs[0] = '(';
        cs[2 * n - 1] = ')';
        recurse(ans, n - 1, n - 1, 1, 1, 1, cs);

        return ans;
    }

    private void recurse(List<String> ans, int l, int r, int lb, int rb, int index, char[] cs) {
        if (l == 0 && r == 0) {
            ans.add(new String(cs));
            return;
        }

        if (l > 0 && r > 0) {
            cs[index] = '(';
            cs[cs.length - 1 - index] = ')';
            recurse(ans, l - 1, r - 1, lb + 1, rb + 1, index + 1, cs);
        }

        if (l > 1 && rb > 0) {
            cs[index] = '(';
            cs[cs.length - 1 - index] = '(';
            recurse(ans, l - 2, r, lb + 1, rb - 1, index + 1, cs);
        }

        if (r > 1 && lb > 0) {
            cs[index] = ')';
            cs[cs.length - 1 - index] = ')';
            recurse(ans, l, r - 2, lb - 1, rb + 1, index + 1, cs);
        }

        if (l > 0 && r > 0 && lb > 0 && rb > 0) {
            cs[index] = ')';
            cs[cs.length - 1 - index] = '(';
            recurse(ans, l - 1, r - 1, lb - 1, rb - 1, index + 1, cs);
        }
    }
}