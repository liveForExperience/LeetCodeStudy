package com.bottomlord.week_193;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chen yue
 * @date 2023-03-30 09:13:23
 */
public class LeetCode_1637_1_两点之间不包含任何点的最宽垂直区域 {
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(x -> x[0]));
        int ans = 0;
        for (int i = 0; i < points.length - 1; i++) {
            ans = Math.max(points[i + 1][0] - points[i][0], ans);
        }
        return ans;
    }
}
