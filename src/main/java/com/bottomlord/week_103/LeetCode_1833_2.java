package com.bottomlord.week_103;

/**
 * @author ChenYue
 * @date 2021/7/2 8:18
 */
public class LeetCode_1833_2 {
    public int maxIceCream(int[] costs, int coins) {
        sort(costs);
        int count = 0;
        for (int cost : costs) {
            coins -= cost;
            if (coins < 0) {
                return count;
            }
            count++;
        }
        return count;
    }

    private void sort(int[] arr) {
        doSort(arr, 0, arr.length - 1);
    }

    private void doSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int partition = partition(arr, start, end);
        doSort(arr, start, partition - 1);
        doSort(arr, partition + 1, end);
    }

    private int partition(int[] arr, int start, int end) {
        while (start < end) {
            while (start < end && arr[start] <= arr[end]) {
                end--;
            }
            swap(arr, start, end);

            while (start < end && arr[start] <= arr[end]) {
                start++;
            }
            swap(arr, start, end);
        }
        return start;
    }

    private void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
