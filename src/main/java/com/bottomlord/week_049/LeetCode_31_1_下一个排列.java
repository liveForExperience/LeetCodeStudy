package com.bottomlord.week_049;

/**
 * @author ChenYue
 * @date 2020/6/11 9:23
 */
public class LeetCode_31_1_下一个排列 {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i >= 1 && nums[i] <= nums[i - 1]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i - 1]) {
                j--;
            }

            swap(i, j, nums);
        }

        reserve(i, nums);
    }

    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reserve(int i, int[] nums) {
        int j = nums.length - 1;
        while (i < j) {
            swap(i, j, nums);
            i++;
            j--;
        }
    }
}
