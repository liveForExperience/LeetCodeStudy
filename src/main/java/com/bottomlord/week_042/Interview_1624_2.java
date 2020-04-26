package com.bottomlord.week_042;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/4/26 8:43
 */
public class Interview_1624_2 {
    public List<List<Integer>> pairSums(int[] nums, int target) {
        Arrays.sort(nums);
        int head = 0, tail = nums.length - 1;
        List<List<Integer>> ans = new ArrayList<>();

        while (head < tail) {
            int sum = nums[head] + nums[tail];

            if (sum < target) {
                head++;
            } else if (sum > target) {
                tail--;
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(nums[head]);
                list.add(nums[tail]);
                ans.add(list);
                head++;
                tail--;
            }
        }

        return ans;
    }
}