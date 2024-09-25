package com.bottomlord.week_206;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-06-19 09:29:04
 */
public class LeetCode_1262_1_可被三整除的最大和 {
    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        int[] arr1 = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE},
              arr2 = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};

        for (int num : nums) {
            sum += num;

            int mod = num % 3;

            if (mod == 1) {
                getMin2(num, arr1);
            }

            if (mod == 2) {
                getMin2(num, arr2);
            }
        }

        if (sum % 3 == 0) {
            return sum;
        }

        return sum % 3 == 1 ? getAns(sum, arr1, arr2) : getAns(sum, arr2, arr1);
    }

    private void getMin2(int num, int[] arr) {
        if (num < arr[0]) {
            arr[1] = arr[0];
            arr[0] = num;
        } else if (num < arr[1]) {
            arr[1] = num;
        }
    }

    private int getAns(int sum, int[] arrA, int[] arrB) {
        if (arrA[0] == Integer.MAX_VALUE) {
            return arrB[1] == Integer.MAX_VALUE ? 0 : sum - arrB[0] - arrB[1];
        } else {
            return arrB[1] == Integer.MAX_VALUE ? sum - arrA[0] : sum - Math.min(arrB[0] + arrB[1], arrA[0]);
        }
    }
}
