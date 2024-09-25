package com.bottomlord.week_057;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/8/4 8:17
 */
public class LeetCode_164_1_最大间距 {
    public int maximumGap(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }

        int ans = Integer.MIN_VALUE;
        quickSort(nums, 0, len - 1);
        for (int i = 0; i < len - 2; i++) {
            ans = Math.max(nums[i + 1] - nums[i], ans);
        }
        return ans;
    }

    private void quickSort(int[] nums, int head, int tail) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(nums, head, tail);

        quickSort(nums, head, pivot - 1);
        quickSort(nums, pivot + 1, tail);
    }

    private int partition(int[] nums, int head, int tail) {
        int num = nums[head];
        while (head < tail) {
            while (head < tail && num <= nums[tail]) {
                tail--;
            }

            nums[head] = nums[tail];

            while (head < tail && num >= nums[head]) {
                head++;
            }

            nums[tail] = nums[head];
        }

        nums[head] = num;
        return head;
    }
}
