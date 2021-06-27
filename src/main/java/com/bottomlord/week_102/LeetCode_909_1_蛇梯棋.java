package com.bottomlord.week_102;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class LeetCode_909_1_蛇梯棋 {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] nums = new int[n * n + 1];
        boolean right = true;
        int index = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = right ? 0 : n - 1;  right ? j < n : j >= 0; j += right ? 1 : -1) {
                nums[index++] = board[i][j];
            }
            right = !right;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);

        while (!queue.isEmpty()) {
            int num = queue.poll();
            int step = map.get(num);
            if (num == n * n) {
                return step;
            }

            for (int i = 1; i <= 6; i++) {
                int nextNum = num + i;
                if (nextNum <= 0 || nextNum > n * n) {
                    continue;
                }

                if (nums[nextNum] != -1) {
                    nextNum = nums[nextNum];
                }

                if (map.containsKey(nextNum)) {
                    continue;
                }

                queue.offer(nextNum);
                map.put(nextNum, step + 1);
            }
        }

        return -1;
    }
}
