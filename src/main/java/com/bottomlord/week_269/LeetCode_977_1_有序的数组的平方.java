package com.bottomlord.week_269;

/**
 * @author chen yue
 * @date 2024-09-08 11:10:34
 */
public class LeetCode_977_1_有序的数组的平方 {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length, l = -1, r = n, index = 0;
        int[] arr = new int[n];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                l = i;
            } else {
                r = i;
                break;
            }
        }

        while (l >= 0 || r < n) {
            if (l < 0) {
                arr[index++] = nums[r] * nums[r];
                r++;
                continue;
            }

            if (r == n) {
                arr[index++] = nums[l] * nums[l];
                l--;
                continue;
            }

            int x = nums[l] * nums[l], y = nums[r] * nums[r];
            if (x <= y) {
                arr[index++] = x;
                l--;
            } else {
                arr[index++] = y;
                r++;
            }
        }

        return arr;
    }
}
