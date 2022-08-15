package com.bottomlord.week_161;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-08-13 21:49:53
 */
public class LeetCode_768_1_最多能完成排序的块II {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length, ans = 1;
        int[] sortedArr = new int[n];
        System.arraycopy(arr, 0, sortedArr, 0, n);
        Arrays.sort(sortedArr);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i], y = sortedArr[i];
            map.put(x, map.getOrDefault(x, 0) + 1);

            if (map.get(x) == 0) {
                map.remove(x);
            }

            map.put(y, map.getOrDefault(y, 0) - 1);
            if (map.get(y) == 0) {
                map.remove(y);
            }

            if (map.isEmpty()) {
                ans++;
            }
        }

        return ans;
    }
}
