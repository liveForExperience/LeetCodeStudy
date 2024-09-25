package com.bottomlord.week_195;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-04-09 11:06:06
 */
public class LeetCode_1125_1_最小的必要团队 {
    private long all;
    private long[][] memo;
    private int[] mask;
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length, m = people.size();
        all = (1L << m) - 1;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < req_skills.length; i++) {
            map.put(req_skills[i], i);
        }

        memo = new long[m][1 << n];
        for (long[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        mask = new int[m];
        for (int i = 0; i < m; i++) {
            for (String skill : people.get(i)) {
                mask[i] |= map.get(skill);
            }
        }

        long res = dfs(m - 1, (1 << n) - 1);
        int[] ans = new int[Long.bitCount(res)];
        for (int i = 0, j = 0; i < m; i++) {
            if (((1 << i) & 1) != 0) {
                ans[j++] = i;
            }
        }
        return ans;
    }

    private long dfs(int i, int j) {
        if (j == 0) {
            return 0;
        }

        if (i < 0) {
            return all;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        long a = dfs(i - 1, j), b = dfs(i, j & ~mask[i]) | (1L << i);
        return memo[i][j] = Long.bitCount(a) > Long.bitCount(b) ? a : b;
    }
}
