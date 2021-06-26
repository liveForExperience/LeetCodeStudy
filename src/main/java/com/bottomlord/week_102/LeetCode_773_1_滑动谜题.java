package com.bottomlord.week_102;

import java.util.*;

public class LeetCode_773_1_滑动谜题 {
    int[][] neighbour = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public int slidingPuzzle(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }

        String status = sb.toString();
        if (Objects.equals(status, "123450")) {
            return 0;
        }

        int step = 0;
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(status);

        Set<String> memo = new HashSet<>();
        memo.add(status);

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                List<String> ss = next(s);
                for (String str : ss) {
                    if (Objects.equals(str, "123450")) {
                        return step;
                    }

                    if (memo.contains(str)) {
                        continue;
                    }

                    queue.offer(str);
                    memo.add(str);
                }
            }
        }

        return -1;
    }

    private List<String> next(String status) {
        List<String> next = new ArrayList<>();
        char[] cs = status.toCharArray();
        int index = status.indexOf('0');
        int[] poses = neighbour[index];
        for (int pos : poses) {
            swap(cs, pos, index);
            next.add(new String(cs));
            swap(cs, pos, index);
        }

        return next;
    }

    private void swap(char[] cs, int x, int y) {
        char c = cs[x];
        cs[x] = cs[y];
        cs[y] = c;
    }
}
