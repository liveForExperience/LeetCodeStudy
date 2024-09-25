package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/1 13:19
 */
public class Interview_0810_2 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0 || image[sr][sc] == newColor) {
            return image;
        }

        int color = image[sr][sc], row = image.length, col = image[0].length;


        dfs(image, sr, sc, row, col, color, newColor);
        return image;
    }

    private void dfs(int[][] image, int x, int y, int row, int col, int color, int newColor) {
        if (x < 0 || x >= row || y < 0 || y >= col || image[x][y] != color) {
            return;
        }

        image[x][y] = newColor;

        dfs(image, x + 1, y, row, col, color, newColor);
        dfs(image, x - 1, y, row, col, color, newColor);
        dfs(image, x, y + 1, row, col, color, newColor);
        dfs(image, x, y - 1, row, col, color, newColor);
    }
}
