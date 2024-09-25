package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-26 20:27:50
 */
public class LeetCode_1539_2 {
    public int findKthPositive(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] - i - 1 >= k) {
                return k + i;
            }
        }

        return k + arr.length;
    }
}