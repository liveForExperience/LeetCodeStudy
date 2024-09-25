package com.bottomlord.week_030;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/1/27 14:58
 */
public class LeetCode_1039_2 {
    public int minScoreTriangulation(int[] A) {
        Map<String, Integer> memo = new HashMap<>();
        return dfs(0, A.length - 1, A, memo);
    }

    private int dfs(int left, int right, int[] arr, Map<String, Integer> memo) {
        if (left + 1 == right) {
            return 0;
        }

        String key = Arrays.toString(new int[]{left, right});
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int ans = Integer.MAX_VALUE;
        for (int i = left + 1; i < right; i++) {
            ans = Math.min(ans, dfs(left, i, arr, memo) + dfs(i, right, arr, memo) + arr[left] * arr[i] * arr[right]);
        }

        memo.put(key, ans);
        return ans;
    }
}