package com.bottomlord.week_039;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/3/30 8:42
 */
public class Interview_0804_1_幂集 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(0, nums, new LinkedList<>(), ans);
        return ans;
    }

    private void backTrack(int index, int[] nums, LinkedList<Integer> path, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(path));

        for (int i = index; i < nums.length; i++) {
            path.offerLast(nums[i]);
            backTrack(i + 1, nums, path, ans);
            path.removeLast();
        }
    }
}
