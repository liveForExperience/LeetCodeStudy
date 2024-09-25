package com.bottomlord.week_184;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-01-20 10:10:03
 */
public class LeetCode_1817_1_查找用户活跃分钟数 {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> mapping = new HashMap<>();
        Set<Integer> memo = new HashSet<>();
        for (int[] log : logs) {
            Integer id = log[0];
            if (memo.contains(id)) {
                continue;
            }

            mapping.computeIfAbsent(id, x -> new HashSet<>()).add(log[1]);

            if (mapping.get(id).size() > k) {
                mapping.remove(id);
                memo.add(id);
            }
        }

        int[] ans = new int[k];
        for (Map.Entry<Integer, Set<Integer>> entry : mapping.entrySet()) {
            int count = entry.getValue().size();
            ans[count - 1]++;
        }

        return ans;
    }
}
