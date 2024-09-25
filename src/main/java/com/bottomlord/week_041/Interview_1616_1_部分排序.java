package com.bottomlord.week_041;

/**
 * @author ChenYue
 * @date 2020/4/13 8:42
 */
public class Interview_1616_1_部分排序 {
    public int[] subSort(int[] array) {
        int len = array.length, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, left = 0, right = len - 1;
        for (int i = 0; i < len; i++) {
            if (array[i] < max) {
                right = i;
            } else {
                max = Math.max(max, array[i]);
            }
        }

        for (int i = len - 1; i >= 0; i--) {
            if (array[i] > min) {
                left = i;
            } else {
                min = Math.min(min, array[i]);
            }
        }

        return new int[]{left, right};
    }
}