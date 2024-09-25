package com.bottomlord.week_063;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/9/20 15:50
 */
public class LeetCode_274_1_H指数 {
    public int hIndex(int[] citations) {
        int len = citations.length;

        quickSort(0, len - 1, citations);

        int i = 0;
        while (i < len && citations[i] > i) {
            i++;
        }

        return i;
    }

    private void quickSort(int head, int tail, int[] arr) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(head, tail, arr);

        quickSort(head, pivot - 1, arr);
        quickSort(pivot + 1, tail, arr);
    }

    private int partition(int head, int tail, int[] arr) {
        int num = arr[head];

        while (head < tail) {
            while (head < tail && arr[tail] <= num) {
                tail--;
            }

            arr[head] = arr[tail];

            while (head < tail && arr[head] >= num) {
                head++;
            }

            arr[tail] = arr[head];
        }

        arr[head] = num;
        return head;
    }
}
