package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/3 12:59
 */
public class Interview_1003_1_搜索旋转数组 {
    public int search(int[] arr, int target) {
        int head = 0, tail = arr.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (arr[head] < arr[mid]) {
                if (arr[head] <= target && target <= arr[mid]) {
                    tail = mid;
                } else {
                    head = mid + 1;
                }
            } else if (arr[head] > arr[mid]) {
                if (arr[head] <= target || target <= arr[mid]) {
                    tail = mid;
                } else {
                    head = mid + 1;
                }
            } else if (arr[head] == arr[mid]) {
                if (arr[head] != target) {
                    head++;
                } else {
                    tail = head;
                }
            }
        }

        return arr[head] == target ? head : -1;
    }
}
