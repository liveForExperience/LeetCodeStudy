package com.bottomlord.week_068;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/10/26 8:36
 */
public class LeetCode_1365_2 {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }

        Arrays.sort(nums);

        for (int i = 0; i < len; i++) {
            if (i != 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            List<Integer> list = map.get(nums[i]);
            for (Integer index : list) {
                ans[index] = i;
            }
        }

        return ans;
    }
}