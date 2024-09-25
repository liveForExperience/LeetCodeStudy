package com.bottomlord.week_072;

/**
 * @author ChenYue
 * @date 2020/11/27 8:34
 */
public class LeetCode_360_1_有序转化数组 {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int len = nums.length, index = 0;
        int[] ans = new int[len];
        int left = 0, right = len - 1;
        while (left <= right) {
            int leftNum = a * nums[left] * nums[left] + b * nums[left] + c,
                rightNum = a * nums[right] * nums[right] + b * nums[right] + c;

            if (a > 0) {
                if (leftNum < rightNum) {
                    ans[len - 1 - index] = rightNum;
                    right--;
                } else {
                    ans[len - 1 - index] = leftNum;
                    left++;
                }
            } else {
                if (leftNum < rightNum) {
                    ans[index] = leftNum;
                    left++;
                } else {
                    ans[index] = rightNum;
                    right--;
                }
            }
            index++;
        }

        return ans;
    }
}
