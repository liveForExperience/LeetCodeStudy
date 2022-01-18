package com.bottomlord.week_132;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-01-18 20:23:43
 */
public class LeetCode_2089_1_找出数组排序后的目标下标 {
    public List<Integer> targetIndices(int[] nums, int target) {
        sort(nums);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                ans.add(i);
            }

            if (nums[i] > target) {
                break;
            }
        }

        return ans;
    }

    private void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int head, int tail) {
        if (head >= tail) {
            return;
        }

        int partition = partition(nums, head, tail);

        quickSort(nums, head, partition - 1);
        quickSort(nums, partition + 1, tail);
    }

    private int partition(int[] nums, int head, int tail) {
        int pivot = nums[head];

        while (head < tail) {
            while (head < tail && nums[tail] >= pivot) {
                tail--;
            }

            nums[head] = nums[tail];

            while (head < tail && nums[head] <= pivot) {
                head++;
            }

            nums[tail] = nums[head];
        }

        nums[head] = pivot;
        return head;
    }
}
