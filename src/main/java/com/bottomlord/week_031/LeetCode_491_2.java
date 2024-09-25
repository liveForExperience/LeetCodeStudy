package com.bottomlord.week_031;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/2/7 21:52
 */
public class LeetCode_491_2 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, new ArrayList<>(), ans, 0, -101);
        return ans;
    }

    private void dfs(int[] nums, List<Integer> list, List<List<Integer>> ans, int index, int lastNum) {
        if (list.size() > 1) {
            ans.add(new ArrayList<>(list));
        }

        for (int i = index; i < nums.length; i++) {
            if (nums[i] < lastNum) {
                continue;
            }

            boolean repeat = false;
            for (int j = index; j <= i - 1; j++) {
                if (nums[i] == nums[j]) {
                    repeat = true;
                    break;
                }
            }

            if (repeat) {
                continue;
            }

            list.add(nums[i]);
            dfs(nums, list, ans, index + 1, nums[i]);
            list.remove(list.size() - 1);
        }
    }
}
