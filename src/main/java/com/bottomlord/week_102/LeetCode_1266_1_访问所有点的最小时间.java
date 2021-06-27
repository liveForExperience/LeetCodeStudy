package com.bottomlord.week_102;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class LeetCode_1266_1_访问所有点的最小时间 {
    public int minTimeToVisitAllPoints(int[][] points) {
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        int step = 0, index = 0, n = points.length;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(points[index]);
        Set<String> memo = new HashSet<>();
        memo.add(points[index][0] + ":" + points[index][1]);
        index++;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                boolean find = false;
                if (arr[0] == points[index][0] && arr[1] == points[index][1]) {
                    index++;
                    if (index == n) {
                        return step;
                    }
                    queue.clear();
                    memo.clear();
                    find = true;
                }

                for (int[] direction : directions) {
                    int[] newArr = new int[]{arr[0] + direction[0], arr[1] + direction[1]};
                    String memoKey = newArr[0] + ":" + newArr[1];
                    if (memo.contains(memoKey)) {
                        continue;
                    }

                    queue.offer(newArr);
                    memo.add(memoKey);
                }

                if (find) {
                    break;
                }
            }

            step++;
        }

        return -1;
    }
}
