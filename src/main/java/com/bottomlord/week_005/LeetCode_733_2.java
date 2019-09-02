package com.bottomlord.week_005;

public class LeetCode_733_2 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (color == newColor) {
            return image;
        }

        image[sr][sc] = newColor;

        if (sr - 1 >= 0 && color == image[sr - 1][sc]) {
            floodFill(image, sr - 1, sc, newColor);
        }

        if (sr + 1 < image.length && color == image[sr + 1][sc]) {
            floodFill(image, sr + 1, sc, newColor);
        }

        if (sc - 1 >= 0 && color == image[sr][sc - 1]) {
            floodFill(image, sr, sc - 1, newColor);
        }

        if (sc + 1 < image[sr].length && color == image[sr][sc + 1]) {
            floodFill(image, sr, sc + 1, newColor);
        }

        return image;
    }
}
