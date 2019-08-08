package com.bottomlord.week_5;

import java.util.*;

public class LeetCode_733_1_图像渲染 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (color == newColor) {
            return image;
        }
        Map<Integer, Set<Integer>> mem = new HashMap<>();
        dfs(image, color, sr, sc, newColor, mem);

        return image;
    }

    private void dfs(int[][] image, int origin, int sr, int sc, int newColor, Map<Integer, Set<Integer>> mem) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[sr].length) {
            return;
        }

        Set<Integer> set = mem.get(sr);
        if (set != null) {
            if (set.contains(sc)) {
                return;
            }
            set.add(sc);
        } else {
            set = new HashSet<>();
            set.add(sc);
        }
        mem.put(sr, set);

        if (image[sr][sc] != origin) {
            return;
        }

        image[sr][sc] = newColor;

        dfs(image, origin, sr + 1, sc, newColor, mem);
        dfs(image, origin, sr - 1, sc, newColor, mem);
        dfs(image, origin, sr, sc + 1, newColor, mem);
        dfs(image, origin, sr, sc - 1, newColor, mem);
    }
}
