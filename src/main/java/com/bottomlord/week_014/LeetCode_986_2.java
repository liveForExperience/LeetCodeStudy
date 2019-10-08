package com.bottomlord.week_014;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_986_2 {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int a = 0, aLen = A.length, b = 0, bLen = B.length;
        List<int[]> list = new ArrayList<>();
        while (a < aLen && b < bLen) {
            if (A[a][1] < B[b][0]) {
                a++;
                continue;
            }

            if (B[b][1] < A[a][0]) {
                b++;
                continue;
            }

            int[] arr = new int[2];
            arr[0] = Math.max(A[a][0], B[b][0]);
            arr[1] = Math.min(A[a][1], B[b][1]);
            list.add(arr);

            if (A[a][1] > B[b][1]) {
                b++;
            } else if (A[a][1] < B[b][1]) {
                a++;
            } else {
                a++;
                b++;
            }
        }

        int[][] ans = new int[list.size()][2];
        int index = 0;
        for (int[] arr : list) {
            ans[index++] = arr;
        }
        return ans;
    }
}