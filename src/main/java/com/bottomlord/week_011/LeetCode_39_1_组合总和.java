package com.bottomlord.week_011;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_39_1_组合总和 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(ans, new ArrayList<>(), candidates, target,0, 0);
        return ans;
    }

    private void backTrack(List<List<Integer>> ans, List<Integer> list, int[] candidates, int target, int index,  int sum) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            backTrack(ans, list, candidates, target, i, sum + candidates[i]);
            list.remove(list.size() - 1);
        }
    }
}
