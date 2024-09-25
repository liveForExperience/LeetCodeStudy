package com.bottomlord.week_225;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-11-01 09:55:04
 */
public class LeetCode_2003_1_每棵子树内缺失的最小基因值 {
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = nums.length, node = -1;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                node = i;
                break;
            }
        }

        if (node == -1) {
            return ans;
        }

        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, x -> new ArrayList<>());
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == -1) {
                continue;
            }

            g[parents[i]].add(i);
        }

        int min = 2;
        Set<Integer> memo = new HashSet<>();
        while (node != -1) {
            dfs(node, nums, g, memo);
            while (memo.contains(min)) {
                min++;
            }
            ans[node] = min;
            node = parents[node];
        }

        return ans;
    }

    private void dfs(int node, int[] nums, List<Integer>[] g, Set<Integer> memo) {
        memo.add(nums[node]);

        for (Integer son : g[node]) {
            if (memo.contains(nums[son])) {
                continue;
            }

            dfs(son, nums, g, memo);
        }
    }
}
