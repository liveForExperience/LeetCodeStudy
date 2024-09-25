package com.bottomlord.week_053;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_315_2 {
    private int[] indexes;
    private int[] counts;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        int len = nums.length;
        if (len == 0) {
            return ans;
        }

        indexes = new int[len];
        counts = new int[len];

        for (int i = 0; i < len; i++) {
            indexes[i] = i;
        }

        mergeSort(nums, 0, len - 1);

        for (int num : counts) {
            ans.add(num);
        }

        return ans;
    }

    private void mergeSort(int[] nums, int head, int tail) {
        if (head == tail) {
            return;
        }

        int mid = head + (tail - head) / 2;
        mergeSort(nums, head, mid);
        mergeSort(nums, mid + 1, tail);

        if (nums[indexes[mid]] > nums[indexes[mid + 1]]) {
            doMergeSort(nums, head, mid, tail);
        }
    }

    private void doMergeSort(int[] nums, int head, int mid, int tail) {
        int[] tmp = new int[nums.length];
        for (int i = head; i <= tail; i++) {
            tmp[i] = indexes[i];
        }

        int i = head, j = mid + 1;
        for (int k = head; k <= tail; k++) {
            if (i > mid) {
                indexes[k] = tmp[j];
                j++;
            } else if (j > tail) {
                indexes[k] = tmp[i];
                i++;
                counts[indexes[k]] += (tail - mid);
            } else if (nums[tmp[i]] <= nums[tmp[j]]) {
                indexes[k] = tmp[i];
                i++;
                counts[indexes[k]] +=( j - mid - 1);
            } else {
                indexes[k] = tmp[j];
                j++;
            }
        }
    }
}