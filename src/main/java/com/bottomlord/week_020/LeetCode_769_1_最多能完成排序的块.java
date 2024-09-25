package com.bottomlord.week_020;

public class LeetCode_769_1_最多能完成排序的块 {
    public int maxChunksToSorted(int[] arr) {
        int count = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                count++;
            }
        }
        return count;
    }
}