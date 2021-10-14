package com.bottomlord.week_118;

/**
 * @author chen yue
 * @date 2021-10-14 08:35:30
 */
public class LeetCode_offerII69_1_山峰数组的顶部 {
    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                return i;
            }
        }

        return arr[0] > arr[arr.length - 1] ? 0 : arr.length - 1;
    }
}
