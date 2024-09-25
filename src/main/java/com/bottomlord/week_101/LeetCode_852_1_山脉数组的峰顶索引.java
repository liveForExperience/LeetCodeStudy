package com.bottomlord.week_101;

/**
 * @author ChenYue
 * @date 2021/6/15 8:18
 */
public class LeetCode_852_1_山脉数组的峰顶索引 {
    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i] && arr[i + 1] < arr[i]) {
                return i;
            }
        }

        return -1;
    }
}
