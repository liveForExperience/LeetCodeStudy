package com.bottomlord.week_064;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/9/25 8:42
 */
public class LeetCode_280_2 {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            int tmp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = tmp;
        }
    }
}
