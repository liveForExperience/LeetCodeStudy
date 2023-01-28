package com.bottomlord.week_185;

/**
 * @author chen yue
 * @date 2023-01-28 10:50:51
 */
public class LeetCode_1664_2 {
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int preOdd = 0, preEven = 0, sufOdd = 0, sufEven = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (i % 2 == 1) {
                sufOdd += num;
            } else {
                sufEven += num;
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            boolean isOdd = i % 2 == 1;
            if (isOdd) {
                sufOdd -= nums[i];
            } else {
                sufEven -= nums[i];
            }

            count += preOdd + sufEven == preEven + sufOdd ? 1 : 0;

            if (isOdd) {
                preOdd += nums[i];
            } else {
                preEven += nums[i];
            }
        }

        return count;
    }
}