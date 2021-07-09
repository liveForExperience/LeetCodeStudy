package com.bottomlord.week_104;

/**
 * @author ChenYue
 * @date 2021/7/9 8:36
 */
public class LeetCode_1287_1_有序数组中出现次数超过25percent的元素 {
    public int findSpecialInteger(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(num, max);
        }

        int[] bucket = new int[max + 1];
        for (int num : arr) {
            bucket[num]++;
            if (bucket[num] > arr.length / 4) {
                return num;
            }
        }
        return -1;
    }
}
