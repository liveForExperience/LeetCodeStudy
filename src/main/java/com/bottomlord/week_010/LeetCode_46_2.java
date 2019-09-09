package com.bottomlord.week_010;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_46_2 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visit = new boolean[nums.length + 1];
        recurse(nums, ans, visit, new ArrayList<>(), nums.length);
        return ans;
    }

    private void recurse(int[]nums, List<List<Integer>> ans, boolean[] visit, List<Integer> list, int len) {
        if (len == list.size()) {
            ans.add(new ArrayList<>(list));
        }

        for (int i = 0; i < len; i++) {
            if (!visit[i]) {
                list.add(nums[i]);
                visit[i] = true;
                recurse(nums, ans, visit, list, len);
                list.remove(list.size() - 1);
                visit[i] = false;
            }
        }
    }
}