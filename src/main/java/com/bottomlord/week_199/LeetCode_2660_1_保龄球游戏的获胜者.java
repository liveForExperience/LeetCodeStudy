package com.bottomlord.week_199;

/**
 * @author chen yue
 * @date 2023-05-03 13:49:39
 */
public class LeetCode_2660_1_保龄球游戏的获胜者 {
    public int isWinner(int[] player1, int[] player2) {
        int x = 0, y = 0, n = player1.length;
        for (int i = 0; i < n; i++) {
            x += getScore(player1, i);
            y += getScore(player2, i);
        }

        return x == y ? 0 : x > y ? 1 : 2;
    }

    private int getScore(int[] players, int index) {
        if (index == 0) {
            return players[index];
        }

        if (index == 1) {
            return players[index - 1] == 10 ? 2 * players[index] : players[index];
        }

        return isTen(players[index - 1], players[index - 2]) ? 2 * players[index] : players[index];
    }

    private boolean isTen(int pre1, int pre2) {
        return pre1 == 10 || pre2 == 10;
    }
}
