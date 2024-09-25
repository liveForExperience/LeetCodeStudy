package com.bottomlord.week_102;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2021/6/22 8:54
 */
public class LeetCode_1243_1_数组变换 {
    public List<Integer> transformArray(int[] arr) {
        boolean flag = true;
        int[] copy = Arrays.copyOf(arr, arr.length);
        while (flag) {
            int count = 0;
            for (int i = 1; i < arr.length - 1; i++) {
                if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) {
                    copy[i]++;
                    count++;
                }

                if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                    copy[i]--;
                    count++;
                }
            }

            arr = copy;
            flag = count != 0;
        }

        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }
}
