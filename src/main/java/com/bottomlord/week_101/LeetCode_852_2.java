package com.bottomlord.week_101;

/**
 * @author ChenYue
 * @date 2021/6/15 8:28
 */
public class LeetCode_852_2 {
    public int peakIndexInMountainArray(int[] arr) {
        int head = 1, tail = arr.length - 2;
        while (head < tail) {
            int mid = (tail - head) / 2 + head;

            if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid]) {
                return mid;
            } else if (arr[mid - 1] < arr[mid]) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return -1;
    }
}
