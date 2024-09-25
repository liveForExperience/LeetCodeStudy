package com.bottomlord.week_098;

/**
 * @author ChenYue
 * @date 2021/5/24 20:30
 */
public class LeetCode_1064_1_不动点 {
    public int fixedPoint(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}
