package com.bottomlord.week_104;

/**
 * @author ChenYue
 * @date 2021/7/9 8:41
 */
public class LeetCode_1287_2 {
    public int findSpecialInteger(int[] arr) {
        for (int i = 0, len = arr.length / 4; i < arr.length - len; i++) {
            if (arr[i] == arr[i + len]) {
                return arr[i];
            }
        }
        return -1;
    }
}
