package com.bottomlord.week_010;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeetCode_46_1_全排列 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 0) {
            return ans;
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        backTrack(nums.length, ans, list, 0);
        return ans;
    }

    private void backTrack(int len, List<List<Integer>> ans, List<Integer> list, int index) {
        if (index == len) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < len; i++) {
            Collections.swap(list, index, i);
            backTrack(len, ans, list, index + 1);
            Collections.swap(list, index, i);
        }
    }
}
