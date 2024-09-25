package com.bottomlord.week_136;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-02-19 12:54:24
 */
public class LeetCode_969_1_煎饼排序 {
    public List<Integer> pancakeSort(int[] arr) {
        int len = arr.length, index = len - 1;
        List<Integer> ans = new ArrayList<>();
        while (index >= 0) {
            int maxIndex = findMaxNumIndex(arr, index);

            if (maxIndex == index) {
                index--;
                continue;
            }

            reserve(arr, 0, maxIndex);
            ans.add(maxIndex + 1);
            reserve(arr, 0, index);
            ans.add(index-- + 1);
        }

        return ans;
    }

    private int findMaxNumIndex(int[] arr, int tail) {
        int max = Integer.MIN_VALUE, maxIndex = -1;
        for (int i = 0; i <= tail; i++) {
            if (max < arr[i]) {
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void reserve(int[] arr, int head, int tail) {
        while (head < tail) {
            int tmp = arr[head];
            arr[head] = arr[tail];
            arr[tail] = tmp;
            head++;
            tail--;
        }
    }
}
