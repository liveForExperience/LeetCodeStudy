package com.bottomlord.week_163;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-08-25 22:08:43
 */
public class LeetCode_658_2 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = binarySearch(arr, x);
        int l = index - 1, r = index;
        while (r - l < k) {
            if (l < 0) {
                r++;
            } else if (r >= arr.length) {
                l--;
            } else if (x - arr[l] > arr[r] - x) {
                r++;
            } else {
                l--;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = l + 1; i < r; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    private int binarySearch(int[] arr, int target) {
        int head = 0, tail = arr.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int num = arr[mid];
            if (num >= target) {
                tail = mid;
            } else {
                head = mid;
            }
        }

        return head;
    }
}