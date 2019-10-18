package com.bottomlord.week_015;

import java.util.*;

public class LeetCode_40_1_组合总和II {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(candidates, target, 0, 0, new LinkedList<>(), ans);
        return ans;
    }

    private void backTrack(int[] candidates, int target, int start, int sum, LinkedList<Integer> list, List<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new LinkedList<>(list));
            return;
        }

        if (sum > target || start >= candidates.length) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            list.addLast(candidates[i]);
            backTrack(candidates, target, i + 1, sum + candidates[i], list, ans);
            list.removeLast();

            while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
                i++;
            }
        }
    }
}
