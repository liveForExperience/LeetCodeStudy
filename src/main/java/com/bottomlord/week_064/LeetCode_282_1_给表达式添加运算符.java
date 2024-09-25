package com.bottomlord.week_064;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/9/27 8:23
 */
public class LeetCode_282_1_给表达式添加运算符 {

    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        dfs(num, target, ans, new StringBuilder(), 0, 0, 0);
        return ans;
    }

    private void dfs(String num, int target, List<String> ans, StringBuilder sb, int start, long eval, long pre) {
        if (start == num.length()) {
            if (eval == target) {
                ans.add(sb.toString());
            }
            return;
        }

        int len = sb.length();
        for (int i = start; i < num.length(); i++) {
            if (num.charAt(start) == '0' && i > start) {
                break;
            }

            long cur = Long.parseLong(num.substring(start, i + 1));

            if (start == 0) {
                dfs(num, target, ans, sb.append(cur), i + 1, cur, cur);
                sb.setLength(len);
            } else {
                dfs(num, target, ans, sb.append("+").append(cur), i + 1, eval + cur, cur);
                sb.setLength(len);

                dfs(num, target, ans, sb.append("-").append(cur), i + 1, eval - cur, cur);
                sb.setLength(len);

                dfs(num, target, ans, sb.append("*").append(cur), i + 1, eval - pre + pre * cur, pre * cur);
                sb.setLength(len);
            }
        }
    }
}
