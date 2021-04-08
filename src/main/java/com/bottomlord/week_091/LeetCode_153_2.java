package com.bottomlord.week_091;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/4/8 8:51
 */
public class LeetCode_153_2 {
    public int findMin(int[] nums) {
        quickSort(0, nums.length - 1, nums);
        return nums[0];
    }

    private void quickSort(int head, int tail, int[] nums) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(head, tail, nums);

        quickSort(head, pivot - 1, nums);
        quickSort(pivot + 1, tail, nums);
    }

    private int partition(int head, int tail, int[] nums) {
        while (head < tail) {
            while (head < tail && nums[tail] >= nums[head]) {
                tail--;
            }
            swap(head, tail, nums);

            while (head < tail && nums[head] <= nums[tail]) {
                head++;
            }
            swap(head, tail, nums);
        }

        return head;
    }

    private void swap(int x, int y, int[] nums) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
