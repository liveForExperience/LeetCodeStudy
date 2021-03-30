package com.bottomlord.week_090;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2021/3/30 8:49
 */
public class LeetCode_505_1_迷宫II {
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int row = maze.length, col = maze[0].length;

        Queue<Node> queue = new ArrayDeque<>();
        int[][] memo = new int[row][col];
        for (int[] arr : memo) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        memo[start[0]][start[1]] = 0;
        queue.offer(new Node(start[0], start[1], 0));
        int ans = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x, y = cur.y;

            for (int[] dir : dirs) {
                int nextX = x + dir[0], nextY = y + dir[1], count = 0;
                while (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && maze[nextX][nextY] != 1) {
                    nextX += dir[0];
                    nextY += dir[1];
                    count++;

                    if (destination[0] == nextX - dir[0] && destination[1] == nextY - dir[1]) {
                        break;
                    }
                }

                if (count == 0) {
                    continue;
                }

                nextX -= dir[0];
                nextY -= dir[1];
                count += cur.count;

                if (count <= memo[nextX][nextY] && count < ans) {
                    memo[nextX][nextY] = count;
                    if (nextX == destination[0] && nextY == destination[1]) {
                        ans = count;
                        break;
                    } else {
                        queue.offer(new Node(nextX, nextY, count));
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private class Node {
        private int count;
        private int x;
        private int y;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}