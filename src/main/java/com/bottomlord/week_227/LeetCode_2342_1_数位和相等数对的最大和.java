package com.bottomlord.week_227;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-11-18 21:53:04
 */
public class LeetCode_2342_1_数位和相等数对的最大和 {
    public int maximumSum(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int ans = -1;
        for (int num : nums) {
            int cnt = Integer.bitCount(num);
            int[] arr = map.getOrDefault(cnt, new int[]{-1, -1});
            if (num > arr[0]) {
                arr[1] = arr[0];
                arr[0] = num;
            } else if (num > arr[1]) {
                arr[1] = num;
            }

            map.put(cnt, arr);

            if (arr[0] == -1 || arr[1] == -1) {
                continue;
            }

            ans = Math.max(ans, arr[0] + arr[1]);
        }

        return ans;
    }
}
