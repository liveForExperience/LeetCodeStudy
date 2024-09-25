package com.bottomlord.week_139;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-03-07 09:15:51
 */
public class LeetCode_593_1_有效的正方形 {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = new int[][]{p1, p2, p3, p4};

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (checkDup(p[i], p[j])) {
                    return false;
                }
            }
        }

        return backTrack(p, 0);
    }

    private boolean checkDup(int[] x, int[] y) {
        return x[0] == y[0] && x[1] == y[1];
    }

    private boolean backTrack(int[][] arr, int index) {
        if (index == 4) {
            return check(arr);
        }

        boolean result = false;
        for (int i = index; i < 4; i++) {
            swap(arr, index, i);
            result |= backTrack(arr, index + 1);
            swap(arr, index, i);
        }
        return result;
    }

    private boolean check(int[][] arr) {
        return dist(arr[0], arr[1]) > 0 &&
               dist(arr[0], arr[1]) == dist(arr[1], arr[2]) &&
               dist(arr[1], arr[2]) == dist(arr[2], arr[3]) &&
               dist(arr[2], arr[3]) == dist(arr[3], arr[0]) &&
               dist(arr[0], arr[2]) == dist(arr[1], arr[3]);
    }

    private void swap(int[][] arr, int x, int y) {
        int[] tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    private int dist(int[] x, int[] y) {
        return (x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]);
    }
}
