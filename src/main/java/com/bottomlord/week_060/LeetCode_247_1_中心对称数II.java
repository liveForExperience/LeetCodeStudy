package com.bottomlord.week_060;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/8/28 8:45
 */
public class LeetCode_247_1_中心对称数II {
    public List<String> findStrobogrammatic(int n) {
        char[][] map = new char[][]{{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
        List<String> ans = new ArrayList<>();
        dfs(new char[n], 0, n - 1, map, ans);
        return ans;
    }

    private void dfs(char[] cs, int head, int tail, char[][] map, List<String> ans) {
        if (head > tail) {
            ans.add(new String(cs));
            return;
        }

        for (char[] pair : map) {
            char c = pair[0];
            if (head == tail && (c == '6' || c == '9')) {
                continue;
            }

            if (head == 0 && pair[0] == '0' && head != tail) {
                continue;
            }

            cs[head] = pair[0];
            cs[tail] = pair[1];
            dfs(cs, head + 1, tail - 1, map, ans);
        }
    }
}
