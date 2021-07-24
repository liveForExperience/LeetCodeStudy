package com.bottomlord.week_106;

public class LeetCode_1349_2 {
    public int findLucky(int[] arr) {
        int[] bucket = new int[501];
        for (int num : arr) {
            bucket[num]++;
        }

        for (int i = bucket.length - 1; i >= 1; i--) {
            if (i == bucket[i]) {
                return i;
            }
        }

        return -1;
    }
}
