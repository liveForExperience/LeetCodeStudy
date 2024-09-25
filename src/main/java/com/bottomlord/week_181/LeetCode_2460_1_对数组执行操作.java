package com.bottomlord.week_181;

/**
 * @author chen yue
 * @date 2022-12-31 14:09:22
 */
public class LeetCode_2460_1_对数组执行操作 {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        int head = 0;
        int tail = n - 1;

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] != 0) {
                if (nums[i] == nums[i + 1]) {
                    nums[i] *= 2;
                    nums[i + 1] = 0;
                    arr[tail--] = 0;
                }

                arr[head++] = nums[i];
            }
        }

        if (nums[n - 1] != 0) {
            arr[head] = nums[n - 1];
        }

        return arr;
    }
}
