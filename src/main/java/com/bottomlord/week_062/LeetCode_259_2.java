package com.bottomlord.week_062;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/9/8 18:43
 */
public class LeetCode_259_2 {
    public int threeSumSmaller(int[] nums, int target) {
        int sum = 0, len = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < len - 2; i++) {
            int newTarget = target - nums[i];

            for (int j = i + 1; j < len - 1; j++) {
                int k = binarySearch(nums, j, newTarget - nums[j]);
                sum += k - j;
            }
        }

        return sum;
    }

    private int binarySearch(int[] nums, int start, int target) {
        int head = start, tail = nums.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return head;
    }
}