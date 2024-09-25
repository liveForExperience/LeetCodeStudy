package com.bottomlord.week_142;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-03-30 20:49:47
 */
public class LeetCode_658_1_找到K个最接近的元素 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int[] copy = Arrays.copyOfRange(arr, 0, arr.length);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.abs(arr[i] - x);
        }

        int n = arr.length;
        int[] sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + arr[i];
        }

        int min = Integer.MAX_VALUE, start = 0, end = 0, target = k * x;

        for (int i = 0; i + k <= n; i++) {
            int cur = Math.abs(sums[i + k] - sums[i] - target);
            if (cur < min) {
                start = i;
                end = i + k - 1;
                min = cur;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            ans.add(copy[i]);
        }
        return ans;
    }
}
