package com.bottomlord.week_066;

/**
 * @author ChenYue
 * @date 2020/10/16 8:30
 */
public class LeetCode_302_2 {
    private int left, right, top, bottom;
    public int minArea(char[][] image, int x, int y) {
        if (image.length == 0 || image[0].length == 0) {
            return 0;
        }

        left = x;
        right = x;
        top = y;
        bottom = y;

        dfs(image, image.length, image[0].length, x, y);

        return (right - left) * (bottom - top);
    }

    private void dfs(char[][] image, int r, int c, int x, int y) {
        if (x < 0 || x >= c || y < 0 || y >= r || image[x][y] == '0') {
            return;
        }

        left = Math.min(left, x);
        right = Math.max(right, x);
        top = Math.min(top, y);
        bottom = Math.max(bottom, y);

        image[x][y] = '0';

        dfs(image, r, c, x + 1, y);
        dfs(image, r, c, x - 1, y);
        dfs(image, r, c, x, y + 1);
        dfs(image, r, c, x, y - 1);
    }
}