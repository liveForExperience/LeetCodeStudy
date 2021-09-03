package com.bottomlord.week_112;

/**
 * @author chen yue
 * @date 2021-09-03 08:26:41
 */
public class LeetCode_interview_1714_2 {
    public int[] smallestK(int[] arr, int k) {
        sort(arr);
        k = Math.min(k, arr.length);
        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }

    private void sort(int[] arr) {
        doSort(arr, 0, arr.length - 1);
    }

    private void doSort(int[] arr, int head, int tail) {
        if (head >= tail) {
            return;
        }

        int piovt = partition(arr, head, tail);

        doSort(arr, head, piovt - 1);
        doSort(arr, piovt + 1, tail);
    }

    private int partition(int[] arr, int head, int tail) {
        while (head < tail) {
            while (head < tail && arr[head] <= arr[tail]) {
                tail--;
            }
            swap(arr, head, tail);

            while (head < tail && arr[head] <= arr[tail]) {
                head++;
            }
            swap(arr, head, tail);
        }

        return head;
    }

    private void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
