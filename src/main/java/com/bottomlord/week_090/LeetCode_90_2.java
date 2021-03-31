package com.bottomlord.week_090;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/3/31 8:51
 */
public class LeetCode_90_2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(nums, 0, ans, new LinkedList<>());
        return ans;
    }

    private void backTrack(int[] nums, int index, List<List<Integer>> ans, LinkedList<Integer> list) {
        ans.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            backTrack(nums, i + 1, ans, list);
            list.removeLast();

            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }
}
