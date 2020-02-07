package com.bottomlord.week_031;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/2/7 19:49
 */
public class LeetCode_491_1_递增子序列 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        LinkedList<Integer> list = new LinkedList<>();
        backTrace(nums, 0, list, ans);
        return new ArrayList<>(ans);
    }

    private void backTrace(int[] nums, int index, LinkedList<Integer> list, Set<List<Integer>> ans) {
        if (index >= nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (list.size() > 0 && list.getLast() > nums[i]) {
                continue;
            }

            list.offerLast(nums[i]);
            if (list.size() > 1) {
                ans.add(list);
            }
            backTrace(nums, i + 1, list, ans);
            list.removeLast();
        }
    }
}
