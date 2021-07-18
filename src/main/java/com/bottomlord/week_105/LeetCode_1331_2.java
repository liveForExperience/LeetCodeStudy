package com.bottomlord.week_105;

public class LeetCode_1331_2 {
    public int[] arrayRankTransform(int[] arr) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : arr) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] bucket = new int[max - min + 1];
        for (int num : arr) {
            bucket[num - min]++;
        }

        int index = 1;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                bucket[i] = index++;
            }
        }

        int[] ans = new int[arr.length];
        index = 0;
        for (int num : arr) {
            ans[index++] = bucket[num - min];
        }
        return ans;
    }
}