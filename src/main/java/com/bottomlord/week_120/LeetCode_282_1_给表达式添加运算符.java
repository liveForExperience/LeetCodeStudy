package com.bottomlord.week_120;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-10-31 21:14:41
 */
public class LeetCode_282_1_给表达式添加运算符 {
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        dfs(num, 0, 0, 0, target, "", ans);
        return ans;
    }

    private void dfs(String s, int index, long pre, long cur, int target, String str, List<String> ans) {
        if (index == s.length()) {
            if (cur == target) {
                ans.add(str);
                return;
            }
        }

        for (int i = index; i < s.length(); i++) {
            if (i != index && s.charAt(index) == '0') {
                continue;
            }

            long num = Long.parseLong(s.substring(index, i + 1));
            if (index == 0) {
                dfs(s, i + 1, num, num, target, str + num, ans);
            } else {
                dfs(s, i + 1, num, cur + num, target, str + "+" + num, ans);
                dfs(s, i + 1, -num, cur - num, target, str + "-" + num, ans);
                long x = pre * num;
                dfs(s, i + 1, x, cur - pre + x, target, str + "*" + num, ans);
            }
        }
    }
}
