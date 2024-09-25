package com.bottomlord.week_126;

import java.util.*;

/**
 * @author chen yue
 * @date 2021-12-09 21:30:15
 */
public class LeetCode_794_1_有效的井字游戏 {
    public boolean validTicTacToe(String[] board) {
        int[][] matrix = new int[3][3];
        LinkedList<int[]> xs = new LinkedList<>(), os = new LinkedList<>();

        for (int i = 0; i < board.length; i++) {
            char[] cs = board[i].toCharArray();
            for (int j = 0; j < cs.length; j++) {
                if (cs[j] == 'X') {
                    xs.add(new int[]{i, j});
                } else if (cs[j] == 'O') {
                    os.add(new int[]{i, j});
                }
            }
        }

        return backTrack(xs, os, true, matrix);
    }

    private boolean backTrack(LinkedList<int[]> xs, LinkedList<int[]> os, boolean isX, int[][] board) {
        if (xs.isEmpty() && os.isEmpty()) {
            return true;
        }

        if (isOver(board)) {
            return false;
        }

        LinkedList<int[]> list = isX ? xs : os;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            int[] arr = list.poll();
            if (arr == null) {
                continue;
            }

            board[arr[0]][arr[1]] += isX ? 1 : -1;
            boolean result = backTrack(xs, os, !isX, board);
            if (result) {
                return true;
            }

            board[arr[0]][arr[1]] -= isX ? 1 : -1;
            list.addLast(arr);
        }

        return false;
    }

    private boolean isOver(int[][] board) {
        for (int i = 0; i < 3; i++) {
            int sum = Arrays.stream(board[i]).sum();
            if (sum == 3 || sum == -3) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += board[j][i];
            }

            if (sum == 3 || sum == -3) {
                return true;
            }
        }

        int pie = board[0][0] + board[1][1] + board[2][2],
            na = board[0][2] + board[1][1] + board[2][0];
        return pie == 3 || pie == -3 || na == 3 || na == -3;
    }
}
