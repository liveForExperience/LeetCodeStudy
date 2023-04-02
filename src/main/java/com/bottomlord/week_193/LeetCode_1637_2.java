package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-03-31 09:01:21
 */
public class LeetCode_1637_2 {
    public int maxWidthOfVerticalArea(int[][] points) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, n = points.length;
        for (int[] point : points) {
            max = Math.max(max, point[0]);
            min = Math.min(min, point[0]);
        }

        int maxN = max - min, bucketLen = Math.max(1, maxN / (n - 1)), bucketCount = maxN / bucketLen + 1;
        int[][] gaps = new int[bucketCount + 1][2];
        for (int[] gap : gaps) {
            gap[0] = Integer.MAX_VALUE;
            gap[1] = Integer.MIN_VALUE;
        }

        for (int[] point : points) {
            int index = (point[0] - min) / bucketLen;
            gaps[index][0] = Math.min(point[0], gaps[index][0]);
            gaps[index][1] = Math.max(point[0], gaps[index][1]);
        }

        int ans = 0, pre = Integer.MAX_VALUE;
        for (int[] gap : gaps) {
            if (gap[0] > gap[1]) {
                continue;
            }

            ans = Math.max(ans, gap[0] - pre);
            pre = gap[1];
        }

        return ans;
    }
}
