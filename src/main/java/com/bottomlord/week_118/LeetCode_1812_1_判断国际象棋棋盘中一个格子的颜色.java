package com.bottomlord.week_118;

/**
 * @author chen yue
 * @date 2021-10-14 08:46:14
 */
public class LeetCode_1812_1_判断国际象棋棋盘中一个格子的颜色 {
    public boolean squareIsWhite(String coordinates) {
        int row = coordinates.charAt(0) - 'a',
            col = coordinates.charAt(1) - '0';

        return (row + col) % 2 != 0;
    }
}
