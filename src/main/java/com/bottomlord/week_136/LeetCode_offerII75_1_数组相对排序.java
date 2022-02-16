package com.bottomlord.week_136;

/**
 * @author chen yue
 * @date 2022-02-16 13:41:12
 */
public class LeetCode_offerII75_1_数组相对排序 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int max = Integer.MIN_VALUE;
        for (int num : arr1) {
            max = Math.max(max, num);
        }

        int[] bucket = new int[max + 1];
        for (int num : arr1) {
            bucket[num]++;
        }

        int[] ans = new int[arr1.length];

        int index = 0;
        for (int num : arr2) {
            for (int i = 0; i < bucket[num]; i++) {
                ans[index++] = num;
            }
            bucket[num] = 0;
        }

        for (int num = 0; num <= max; num++) {
            for (int i = 0; i < bucket[num]; i++) {
                ans[index++] = num;
            }
        }

        return ans;
    }
}
