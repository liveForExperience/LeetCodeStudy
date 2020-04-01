package com.bottomlord.week_039;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/4/1 8:41
 */
public class Interview_0809_1_括号 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(ans, 0, 0, n, new StringBuilder());
        return ans;
    }

    private void dfs(List<String> list, int left, int right, int n, StringBuilder sb) {
        if (left + right == n * 2) {
            list.add(sb.toString());
            return;
        }

        if (left < n) {
            dfs(list, left + 1, right, n, sb.append('('));
            sb.deleteCharAt(sb.length() - 1);
        }

        if (right < n && right < left) {
            dfs(list, left, right + 1, n, sb.append(')'));
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
