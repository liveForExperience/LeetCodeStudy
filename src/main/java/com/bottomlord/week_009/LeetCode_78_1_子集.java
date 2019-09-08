package com.bottomlord.week_009;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeetCode_78_1_子集 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = Collections.emptyList();
        ans.add(list);
        rescurse(nums, 0, list, ans);
        return ans;
    }

    private void rescurse(int[] nums, int index, List<Integer> preList, List<List<Integer>> ans) {
        if (index >= nums.length) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            List<Integer> list = new ArrayList<>(preList);
            list.add(nums[i]);
            ans.add(list);
            rescurse(nums, i, list, ans);
        }
    }
}
