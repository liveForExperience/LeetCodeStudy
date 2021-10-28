package com.bottomlord.week_120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-10-28 08:46:15
 */
public class LeeCode_46_1_全排列II {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backTrack(nums, new boolean[nums.length], new LinkedList<>(), ans);
        return ans;
    }

    private void backTrack(int[] nums, boolean[] memo, LinkedList<Integer> arr, List<List<Integer>> ans) {
        if (arr.size() == nums.length) {
            ans.add(new ArrayList<>(arr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (memo[i] || (i > 0 && nums[i] == nums[i - 1] && !memo[i - 1])) {
                continue;
            }

            arr.addLast(nums[i]);
            memo[i] = true;
            backTrack(nums, memo, arr, ans);
            memo[i] = false;
            arr.removeLast();
        }
    }
}
