package com.bottomlord.week_064;

/**
 * @author ChenYue
 * @date 2020/9/25 8:58
 */
public class LeetCode_280_3 {
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0) == (nums[i] > nums[i + 1])) {
                int tmp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = tmp;
            }
        }
    }
}
