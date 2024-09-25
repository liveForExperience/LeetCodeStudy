package com.bottomlord.week_029;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ThinkPad
 * @date 2020/1/20 9:03
 */
public class LeetCode_452_1_最少数量的箭引爆气球 {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        Arrays.sort(points, Comparator.comparingInt(x -> x[1]));
        int start, end, preEnd = points[0][1], ans = 1;
        for (int[] point : points) {
            start = point[0];
            end = point[1];

            if (start > preEnd) {
                ans++;
                preEnd = end;
            }
        }
        return ans;
    }
}