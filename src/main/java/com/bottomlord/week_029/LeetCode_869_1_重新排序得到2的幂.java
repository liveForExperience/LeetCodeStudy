package com.bottomlord.week_029;

/**
 * @author ThinkPad
 * @date 2020/1/21 13:38
 */
public class LeetCode_869_1_重新排序得到2的幂 {
    public boolean reorderedPowerOf2(int N) {
        int b = 0, num = N, index = 0;
        while (num > 0) {
            num /= 10;
            b++;
        }

        int[] arr = new int[b];
        while (N > 0) {
            arr[index++] = N % 10;
            N /= 10;
        }

        return backTrace(arr, 0);
    }

    private boolean isPowerOf2(int[] arr) {
        if (arr[0] == 0) {
            return false;
        }

        int num = 0;
        for (int n : arr) {
            num = num * 10 + n;
        }

        while (num > 0 && (num & 1) == 0) {
            num >>= 1;
        }

        return num == 1;
    }

    private boolean backTrace(int[] arr, int start) {
        if (start == arr.length) {
            return isPowerOf2(arr);
        }

        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            if (backTrace(arr, start + 1)) {
                return true;
            }
            swap(arr, start, i);
        }

        return false;
    }

    private void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
