package com.bottomlord.week_010;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_22_1_括号生成 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        recurse(ans, n, n, new String[2 * n]);
        return ans;
    }

    private void recurse(List<String> ans, int left, int right, String[] strings) {
        if (left == 0 && right == 0) {
            ans.add(String.join("", strings));
            return;
        }

        if (left == right) {
            strings[strings.length - left - right] = "(";
            recurse(ans, left - 1, right, strings);
        } else if (left < right) {
            if (left != 0) {
                strings[strings.length - left - right] = "(";
                recurse(ans, left - 1, right, strings);
            }
            strings[strings.length - left - right] = ")";
            recurse(ans, left, right - 1, strings);
        }
    }
}
