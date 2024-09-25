package com.bottomlord.week_014;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_986_1_区间列表的交集 {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<Integer> listA = getList(A);
        List<Integer> listB = getList(B);

        int a = 0, aLen = listA.size(), b = 0, bLen = listB.size();

        List<int[]> list = new ArrayList<>();
        while (a < aLen && b < bLen) {
            while (a < aLen && listA.get(a) < listB.get(b)) {
                a++;
            }

            if (a >= aLen) {
                break;
            }

            while (b < bLen && listB.get(b) < listA.get(a)) {
                b++;
            }

            if (b >= bLen) {
                break;
            }

            if (!listA.get(a).equals(listB.get(b))) {
                continue;
            }

            int[] arr = new int[]{listA.get(a), 0};

            do {
                a++;
                b++;
            } while (a < aLen && b < bLen && listA.get(a).equals(listB.get(b)));

            arr[1] = listA.get(a - 1);

            list.add(arr);
        }

        int[][] ans = new int[list.size()][2];
        int index = 0;
        for (int[] arr : list) {
            ans[index++] = arr;
        }
        return ans;
    }

    private List<Integer> getList(int[][] arr) {
        List<Integer> listA = new ArrayList<>();
        for (int[] a : arr) {
            for (int i = a[0]; i <= a[1]; i++) {
                listA.add(i);
            }
        }
        return listA;
    }
}
