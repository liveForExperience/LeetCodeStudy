package com.bottomlord.week_040;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/4/8 8:46
 */
public class Interview_1604_1_井字游戏 {
    public String tictactoe(String[] board) {
        int n = board.length;
        boolean blank = false;
        String[] ss = new String[]{"X", "O"};
        for (int i = 0; i < n; i++) {
            if (!blank) {
                if (board[i].contains(" ")) {
                    blank = true;
                }
            }

            for (int j = 0; j < ss.length; j++) {
                if ("".equals(board[i].replace(ss[j], ""))) {
                    return ss[j];
                }
            }

            boolean flag = true;
            for (int j = 1; j < n; j++) {
                if (board[j].charAt(i) == ' ' || !Objects.equals(board[j].charAt(i), board[j - 1].charAt(i))) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                return Character.toString(board[0].charAt(i));
            }
        }

        if (board[0].charAt(0) != ' ') {
            boolean flag = true;
            for (int i = 1; i < n; i++) {
                if (board[i].charAt(i) != board[i - 1].charAt(i - 1)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return Character.toString(board[0].charAt(0));
            }
        }

        if (board[0].charAt(n - 1) != ' ') {
            boolean flag = true;
            for (int i = 1; i < n; i++) {
                if (board[i].charAt(n - 1 - i) != board[i - 1].charAt(n - i)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return Character.toString(board[0].charAt(n - 1));
            }
        }

        return blank ? "Pending" : "Draw";
    }
}
