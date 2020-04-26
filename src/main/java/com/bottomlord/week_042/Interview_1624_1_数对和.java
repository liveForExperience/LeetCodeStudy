package com.bottomlord.week_042;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/4/26 8:29
 */
public class Interview_1624_1_数对和 {
    public List<List<Integer>> pairSums(int[] nums, int target) {
        int len = nums.length;
        boolean[] memo = new boolean[len];
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (memo[i] || memo[j]) {
                    continue;
                }

                if (nums[i] + nums[j] == target) {
                    memo[i] = true;
                    memo[j] = true;
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    ans.add(list);
                }
            }
        }

        return ans;
    }
}
