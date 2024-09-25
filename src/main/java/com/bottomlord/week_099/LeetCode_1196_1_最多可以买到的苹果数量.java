package com.bottomlord.week_099;

import java.util.Arrays;

public class LeetCode_1196_1_最多可以买到的苹果数量 {
    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int count = 0, total = 0;
        for (int num : arr) {
            total += num;
            if (total <= 5000) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
