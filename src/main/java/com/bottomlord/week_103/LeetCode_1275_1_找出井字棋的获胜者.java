package com.bottomlord.week_103;

/**
 * @author ChenYue
 * @date 2021/7/1 8:48
 */
public class LeetCode_1275_1_找出井字棋的获胜者 {
    public String tictactoe(int[][] moves) {
        int count = 0, pie = 0, na = 0;
        int[] row = new int[3], col = new int[3];
        for (int i = 0; i < moves.length; i++) {
            count++;
            int[] move = moves[i];

            boolean flag = i % 2 == 0;
            row[move[0]] += flag ? 1 : -1;
            col[move[1]] += flag ? 1 : -1;
            if (move[0] == move[1]) {
                pie += flag ? 1 : -1;
            }

            if (move[0] + move[1] == 2) {
                na += flag ? 1 : -1;
            }

            if (row[move[0]] == 3 || col[move[1]] == 3 || pie == 3 || na == 3) {
                return "A";
            }

            if (row[move[0]] == -3 || col[move[1]] == -3 || pie == -3 || na == -3) {
                return "B";
            }
        }

        return count == 9 ? "Draw" : "Pending";
    }
}
