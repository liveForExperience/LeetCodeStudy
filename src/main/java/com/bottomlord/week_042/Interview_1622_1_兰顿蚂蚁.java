package com.bottomlord.week_042;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/4/25 14:07
 */
public class Interview_1622_1_兰顿蚂蚁 {
    private int dir = 0, top = 0, bottom = 0, left = 0, right = 0;
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private String[] dirChars = {"R", "D", "L", "U"};
    private Map<String, String> map = new HashMap<>();

    public List<String> printKMoves(int K) {
        recurse(0, 0, K);
        List<String> ans = new ArrayList<>();
        for (int i = top; i <= bottom; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = left; j <= right; j++) {
                String path = i + " " + j;
                sb.append(map.getOrDefault(path, "_"));
            }
            ans.add(sb.toString());
        }

        return ans;
    }

    private void recurse(int x, int y, int K) {
        top = Math.min(x, top);
        bottom = Math.max(x, bottom);
        left = Math.min(y, left);
        right = Math.max(y, right);

        if (K == 0) {
            map.put(x + " " + y, dirChars[dir]);
            return;
        }

        String path = x + " " + y;
        String str = map.getOrDefault(path, "_");

        map.put(path, Objects.equals(str, "_") ? "X" : "_");

        if (Objects.equals(str, "_")) {
            dir = (dir + 1) % 4;
        } else {
            dir = (dir + 3) % 4;
        }

        recurse(x + dirs[dir][0], y + dirs[dir][1], K - 1);
    }
}
