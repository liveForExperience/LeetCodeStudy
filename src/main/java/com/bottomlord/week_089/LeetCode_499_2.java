package com.bottomlord.week_089;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.TreeMap;

/**
 * @author ChenYue
 * @date 2021/3/24 18:48
 */
public class LeetCode_499_2 {
    private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int row = maze.length, col = maze[0].length;
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        int[][] memo = new int[row][col];
        for (int[] arr : memo) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        Queue<Node> queue = new ArrayDeque<>();
        Node node = new Node(ball[0], ball[1], 0, "");
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur == null) {
                continue;
            }

            for (int i = 0; i < dirs.length; i++) {
                int x = cur.x, y = cur.y;
                int[] dir = dirs[i];
                int count = 0;
                int nextX = x + dir[0], nextY = y + dir[1];

                while (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && maze[nextX][nextY] != 1) {
                    nextX += dir[0];
                    nextY += dir[1];
                    count++;

                    if (nextX - dir[0] == hole[0] && nextY - dir[1] == hole[1]) {
                        break;
                    }
                }

                if (count == 0) {
                    continue;
                }

                count += cur.count;

                x = nextX - dir[0];
                y = nextY -  dir[1];
                Node newNode = new Node(x, y, count, cur.output + getOutput(i));
                if (count < memo[x][y]) {
                    memo[x][y] = count;
                    if (x == hole[0] && y == hole[1]) {
                        treeMap.clear();
                        treeMap.put(newNode.output, newNode.count);
                        break;
                    } else {
                        queue.offer(newNode);
                    }
                } else if (count == memo[x][y]) {
                    if (x == hole[0] && y == hole[1]) {
                        treeMap.put(newNode.output, newNode.count);
                        break;
                    } else {
                        queue.offer(newNode);
                    }
                }
            }
        }

        return treeMap.isEmpty() ? "impossible" : treeMap.firstKey();
    }

    private String getOutput(int index) {
        return index == 0 ? "r" : (index == 1 ? "l" : (index == 2 ? "d" : "u"));
    }

    static class Node {
        private final Integer x;
        private final Integer y;
        private final int count;
        private final String output;

        public Node(Integer x, Integer y, int count, String output) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.output = output;
        }
    }
}
