package com.bottomlord.week_3;

/**
 * @author LiveForExperience
 * @date 2019/7/23 11:14
 */
public class LeetCode_1122_1_数组的相对排序 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int min = arr1[0], max = arr1[0];
        for (int i = 1; i < arr1.length; i++) {
            min = Math.min(arr1[i], min);
            max = Math.max(arr1[i], max);
        }

        int[] buckets = new int[max - min + 1];
        for (int num : arr1) {
            buckets[num - min]++;
        }

        int[] ans = new int[arr1.length];
        int index = 0;
        for (int num : arr2) {
            while (buckets[num - min]-- > 0) {
                ans[index++] = num;
            }
        }

        for (int i = 0; i < buckets.length; i++) {
            while (buckets[i]-- > 0) {
                ans[index++] = i + min;
            }
        }

        return ans;
    }
}
