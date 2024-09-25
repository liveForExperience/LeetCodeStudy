package com.bottomlord.week_041;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/4/16 8:10
 */
public class LeetCode_56_2 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }

        int max = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            max = Math.max(interval[1], max);
        }

        int[] arr = new int[max + 1];
        for (int[] interval : intervals) {
            for (int i = interval[0]; i < interval[1]; i++) {
                arr[i] = 1;
            }

            if (arr[interval[1]] != 1) {
                arr[interval[1]] = -1;
            }
        }

        List<int[]> ans = new ArrayList<>();
        int[] tmp = new int[2];
        boolean flag = false;
        for (int i = 0; i <= max; i++) {
            if ((i == 0 || !flag) && arr[i] == 1) {
                flag = true;
                tmp = new int[2];
                tmp[0] = i;
            }

            if (arr[i] == -1) {
                if (flag) {
                    tmp[1] = i;
                    ans.add(tmp);
                } else {
                    ans.add(new int[]{i, i});
                }

                flag = false;
            }
        }

        return ans.toArray(new int[0][0]);
    }
}