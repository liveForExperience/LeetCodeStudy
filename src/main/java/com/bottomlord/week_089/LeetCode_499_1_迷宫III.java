package com.bottomlord.week_089;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/3/24 8:29
 */
public class LeetCode_499_1_迷宫III {
    private int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private String[] dirStrs = {"l", "u", "r", "d"};
    private int min = Integer.MAX_VALUE;
    private String ans;

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int row = maze.length;
        if (row == 0) {
            return null;
        }

        int col = maze[0].length;
        if (col == 0) {
            return null;
        }

        for (int i = 0; i < dirs.length; i++) {
            int newX = ball[0] + dirs[i][0], newY = ball[1] + dirs[i][1];
            if (outOfBound(newX, newY, row, col) || maze[newX][newY] == 1) {
                continue;
            }
            backTrack(maze, ball[0], ball[1], row, col, hole, i, new HashSet<>(), 0, new StringBuilder(dirStrs[i]));
        }

        return ans;
    }

    private void backTrack(int[][] maze, int x, int y, int row, int col, int[] destination, int dirIndex, Set<String> memo, int step, StringBuilder sb) {
        if (step > min ||  Objects.equals(sb.toString(), ans)) {
            return;
        }

        if (x == destination[0] && y == destination[1]) {
            if (min == step) {
                ans = compareAndGet(ans, sb.toString());
            } else {
                ans = sb.toString();
            }
            min = step;
            return;
        }

        int nextX = x + dirs[dirIndex][0], nextY = y + dirs[dirIndex][1];
        if (outOfBound(nextX, nextY, row, col) || maze[nextX][nextY] == 1) {
            for (int i = 0; i < dirs.length; i++) {
                int newX = x + dirs[i][0], newY = y + dirs[i][1];
                String memoKey = initKey(x, y, i);
                if (outOfBound(newX, newY, row, col) || maze[newX][newY] == 1 || memo.contains(memoKey)) {
                    continue;
                }

                memo.add(memoKey);
                int sbLen = sb.length();
                sb.append(dirStrs[i]);
                backTrack(maze, newX, newY, row, col, destination, i, memo, step + 1, sb);
                sb.setLength(sbLen);
                memo.remove(memoKey);
            }
        } else {
            backTrack(maze, nextX, nextY, row, col, destination, dirIndex, memo, step + 1, sb);
        }
    }

    private String compareAndGet(String a, String b) {
        if (a == null) {
            return b;
        }

        char[] as = a.toCharArray(), bs = b.toCharArray();
        int ai = 0, bi = 0;
        while (ai < as.length && bi < bs.length) {
            if (as[ai] < bs[bi]) {
                return a;
            } else if (as[ai] > bs[bi]) {
                return b;
            }

            ai++;
            bi++;
        }

        return a;
    }

    private boolean outOfBound(int x, int y, int row, int col) {
        return x < 0 || x >= row || y < 0 || y >= col;
    }

    private String initKey(int x, int y, int dir) {
        return x + "#" + y + "#" + dir;
    }
}
