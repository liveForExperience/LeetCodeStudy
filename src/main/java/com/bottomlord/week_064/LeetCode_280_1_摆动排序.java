package com.bottomlord.week_064;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/9/24 8:42
 */
public class LeetCode_280_1_摆动排序 {
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }

        int[] copy = Arrays.copyOf(nums, len);
        quickSort(copy, 0, len - 1);
        int head = 0, tail = len - 1;

        int i = 0;
        while (head <= tail) {
            if (head == tail) {
                nums[i] = copy[head];
            } else {
                nums[i++] = copy[head++];
                nums[i++] = copy[tail--];
            }
        }
    }

    private void quickSort(int[] nums, int head, int tail) {
        if (head >= tail) {
            return;
        }
        int index = partition(nums, head, tail);

        quickSort(nums, head, index - 1);
        quickSort(nums, index + 1, tail);
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
