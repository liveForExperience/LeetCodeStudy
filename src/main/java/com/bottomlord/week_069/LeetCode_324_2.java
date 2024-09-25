package com.bottomlord.week_069;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/11/6 9:55
 */
public class LeetCode_324_2 {
    public void wiggleSort(int[] nums) {
        int midIndex = quickSelect(nums, 0, nums.length - 1);
        int mid = nums[midIndex];

        int l = 0, r = nums.length - 1, k = 0;
        while (l <= r) {
            if (nums[l] > mid) {
                swap(nums, l, r);
                r--;
            } else if (nums[l] < mid) {
                swap(nums, l, k);
                l++;
                k++;
            } else {
                l++;
            }
        }

        mid = nums.length % 2 == 1 ? mid : mid + 1;
        int[] partA = Arrays.copyOfRange(nums, 0, mid),
              partB = Arrays.copyOfRange(nums, mid, nums.length);

        for (int i = 0; i < partA.length; i++) {
            nums[2 * i] = partA[partA.length - 1 - i];
        }

        for (int i = 0; i < partB.length; i++) {
            nums[2 * i + 1] = partB[partB.length - 1 - i];
        }
    }

    private int quickSelect(int[] nums, int l, int r) {
        int pivot = nums[l];

        int head = l, tail = r;
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

        if (head == nums.length / 2) {
            return head;
        }

        if (head < nums.length / 2) {
            return quickSelect(nums, head + 1, r);
        } else {
            return quickSelect(nums, l, head - 1);
        }
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
