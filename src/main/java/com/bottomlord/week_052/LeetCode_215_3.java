package com.bottomlord.week_052;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/6/30 8:25
 */
public class LeetCode_215_3 {
    public int findKthLargest(int[] nums, int k) {
        int[] arr = new int[nums.length + 1];
        System.arraycopy(nums, 0, arr, 1, nums.length);
        build(arr);

        int heapSize = arr.length - 1;
        for (int i = arr.length - 1; i >= arr.length - k + 1; i--) {
            swap(arr, 1, i);
            heapSize--;
            heapfiy(arr, 1, heapSize);
        }

        return arr[1];
    }

    private void build(int[] arr) {
        for (int i = arr.length / 2; i > 0; i--) {
            heapfiy(arr, i, arr.length);
        }
    }

    private void heapfiy(int[] arr, int index, int len) {
        int largest = index, left = index * 2, right = index * 2 + 1;

        if (left < len && arr[index] < arr[left]) {
            largest = left;
        }

        if (right < len && arr[index] < arr[right]) {
            largest = right;
        }

        if (largest != index) {
            swap(arr, index, largest);
            heapfiy(arr, largest, len);
        }
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}