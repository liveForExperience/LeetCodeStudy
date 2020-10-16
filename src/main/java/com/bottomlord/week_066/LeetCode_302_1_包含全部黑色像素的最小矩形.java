package com.bottomlord.week_066;

/**
 * @author ChenYue
 * @date 2020/10/16 8:15
 */
public class LeetCode_302_1_包含全部黑色像素的最小矩形 {
    public int minArea(char[][] image, int x, int y) {
        int left = x, right = x,
            top = y, bottom = y;

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (image[i][j] == '1') {
                    left = Math.min(left, i);
                    right = Math.max(right, i + 1);
                    top = Math.min(top, j);
                    bottom = Math.max(bottom, j + 1);
                }
            }
        }

        return (right - left) * (top - bottom);
    }
}
