package com.bottomlord.week_034;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/2/28 12:48
 */
public class Interview_40_3 {
    public int[] getLeastNumbers(int[] arr, int k) {
        quickSelect(arr, k, 0, arr.length - 1);
        return Arrays.copyOf(arr, k);
    }

    private void quickSelect(int[] arr, int k, int start, int end) {
        if (start >= end) {
            return;
        }

        int left = start, right = end, pivot = arr[start + (end - start) / 2];
        while (left <= right) {
            while (left  <= right && arr[left] < pivot) {
                left++;
            }

            while (left <= right && arr[right] > pivot) {
                right--;
            }

            if (left <= right) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }

        }
        if (right > k) {
            quickSelect(arr, k, start, right);
        } else {
            quickSelect(arr, k, left, end);
        }
    }
}