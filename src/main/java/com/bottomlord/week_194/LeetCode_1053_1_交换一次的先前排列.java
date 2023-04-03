package com.bottomlord.week_194;

/**
 * @author chen yue
 * @date 2023-04-03 08:59:02
 */
public class LeetCode_1053_1_交换一次的先前排列 {
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        for (int i = n - 1; i > 0; i--) {
            if (arr[i] > arr[i + 1]) {
                boolean find = false;
                int index = -1;
                for (int j = i + 1; j < n; j++) {
                    if (arr[i] > arr[j]) {
                        find = true;
                        index = j;
                    }
                }

                if (find) {
                    int tmp = arr[index];
                    arr[index] = arr[i];
                    arr[i] = tmp;
                    return arr;
                }
            }
        }
        return arr;
    }
}
