package com.bottomlord.week_052;

/**
 * @author ChenYue
 * @date 2020/6/29 8:28
 */
public class LeetCode_215_1_数组中的第K个最大元素 {
    public int findKthLargest(int[] nums, int k) {
        return quickSearch(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSearch(int[] nums, int start, int end, int index) {
        int pivot = partition(nums, start, end);

        if (pivot == index) {
            return nums[pivot];
        } else {
            return pivot < index ? quickSearch(nums, pivot + 1, end, index) : quickSearch(nums, start, pivot - 1, index);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int num = nums[start];

        while (start < end) {
            while (start < end && nums[end] >= num) {
                end--;
            }
            nums[start] = nums[end];

            while (start < end && nums[start] <= num) {
                start++;
            }
            nums[end] = nums[start];
        }

        nums[start] = num;
        return start;
    }
}
