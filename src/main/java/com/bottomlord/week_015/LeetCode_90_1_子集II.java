package com.bottomlord.week_015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_90_1_子集II {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(nums, 0, new LinkedList<>(), ans);
        return ans;
    }

    private void backTrack(int[] nums, int index, LinkedList<Integer> list, List<List<Integer>> ans) {
        ans.add(new LinkedList<>(list));

        for (int i = index; i < nums.length; i++) {
            list.addLast(nums[i]);
            backTrack(nums, i + 1, list, ans);
            list.removeLast();

            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }
}
