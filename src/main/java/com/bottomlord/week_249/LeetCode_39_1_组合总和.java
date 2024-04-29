package com.bottomlord.week_249;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-04-20 14:04:58
 */
public class LeetCode_39_1_组合总和 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(0, candidates, 0, target, new ArrayList<>(), ans);
        return ans;
    }

    private void backTrack(int index, int[] candidates, int sum, int target, List<Integer> list, List<List<Integer>> ans) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if (index >= candidates.length) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            sum += candidates[i];
            list.add(candidates[i]);
            backTrack(i, candidates, sum, target, list, ans);
            list.remove(list.size() - 1);
            sum -= candidates[i];
        }
    }
}
