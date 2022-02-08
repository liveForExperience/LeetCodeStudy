package com.bottomlord.week_135;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-02-08 23:12:25
 */
public class LeetCode_2164_1_对奇偶下标分别排序 {
    public int[] sortEvenOdd(int[] nums) {
        int n = nums.length;
        boolean odd = n % 2 == 1;
        int oddLen = odd ? n / 2 + 1 : n / 2,
            evenLen = n / 2;

        int[] odds = new int[oddLen], evens = new int[evenLen];
        int oddIndex = 0, evenIndex = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                evens[evenIndex++] = nums[i];
            } else {
                odds[oddIndex++] = nums[i];
            }
        }

        Arrays.sort(odds);
        Arrays.sort(evens);

        oddIndex = oddLen - 1;
        evenIndex = 0;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = evens[evenIndex++];
            } else {
                nums[i] = odds[oddIndex--];
            }
        }

        return nums;
    }
}
