package com.bottomlord.week_029;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/1/23 10:37
 */
public class LeetCode_1090_1_受标签影响的最大值 {
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int[][] arrs = new int[values.length][2];
        for (int i = 0; i < values.length; i++) {
            arrs[i][0] = values[i];
            arrs[i][1] = labels[i];
        }

        Arrays.sort(arrs, (x, y) -> y[0] - x[0]);
        int[] bucket = new int[20001];
        int ans = 0;

        for (int[] arr : arrs) {
            if (num_wanted == 0) {
                break;
            }

            if (bucket[arr[1]] < use_limit) {
                ans += arr[0];
                bucket[arr[1]]++;
                num_wanted--;
            }
        }

        return ans;
    }
}
