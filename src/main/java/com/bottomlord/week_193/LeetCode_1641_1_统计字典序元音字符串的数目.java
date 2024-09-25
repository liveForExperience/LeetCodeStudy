package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-03-29 09:04:27
 */
public class LeetCode_1641_1_统计字典序元音字符串的数目 {
    private int cnt;

    public int countVowelStrings(int n) {
        dfs(0, 0, n);
        return cnt;
    }

    private void dfs(int index, int len, int n) {
        if (len == n) {
            cnt++;
            return;
        }

        for (int i = index; i < 5; i++) {
            dfs(i, len + 1, n);
        }
    }
}
