package com.bottomlord.week_189;

/**
 * @author chen yue
 * @date 2023-02-26 19:40:12
 */
public class LeetCode_2566_1_替换一个数字后的最大差值 {
    public int minMaxDifference(int num) {
        int len = 0;
        int tmp = num;
        while (tmp > 0) {
            tmp /= 10;
            len++;
        }

        int[] arr = new int[len];
        int index = len -1;
        while (num > 0) {
            arr[index--] = num % 10;
            num /= 10;
        }

        int x = -1, y = -1;
        for (int k : arr) {
            if (k != 9 && x == -1) {
                x = k;
            }

            if (k != 0 && y == -1) {
                y = k;
            }
        }

        int smallest = 0, biggest = 0;
        for (int k : arr) {
            int cs = k, cb = k;
            if (k == x) {
                cb = 9;
            }

            if (k == y) {
                cs = 0;
            }

            smallest = smallest * 10 + cs;
            biggest = biggest * 10 + cb;
        }

        return biggest - smallest;
    }
}
