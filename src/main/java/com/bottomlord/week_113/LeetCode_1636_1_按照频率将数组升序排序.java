package com.bottomlord.week_113;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2021-09-07 09:00:54
 */
public class LeetCode_1636_1_按照频率将数组升序排序 {
    public int[] frequencySort(int[] nums) {
        int len = nums.length;
        Integer[] arr = new Integer[len],
                  feqs = new Integer[201];

        for (int i = 0; i < len; i++) {
            arr[i] = nums[i];
            feqs[nums[i] + 100]++;
        }

        Arrays.sort(arr, (x, y) -> {
            if (Objects.equals(feqs[x + 100], feqs[y + 100])) {
                return y - x;
            }

            return feqs[x + 100] - feqs[y + 100];
        });

        for (int i = 0; i < len ; i++) {
            nums[i] = arr[i];
        }

        return nums;
    }
}
