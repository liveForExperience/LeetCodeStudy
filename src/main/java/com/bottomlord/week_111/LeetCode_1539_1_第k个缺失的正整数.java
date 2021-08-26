package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-26 20:15:46
 */
public class LeetCode_1539_1_第k个缺失的正整数 {
    public int findKthPositive(int[] arr, int k) {
        int index = 0, n = arr.length, count = 0;
        for (int i = 1; i <= 1000; i++) {
            if (index < n && i == arr[index]) {
                index++;
            } else {
                count++;
            }

            if (count == k) {
                return i;
            }
        }

        return 2000;
    }
}
