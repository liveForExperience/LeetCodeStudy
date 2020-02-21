package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/20 20:45
 */
public class LeetCode_65_2 {
    public int make(char c) {
        switch (c) {
            case ' ':
                return 0;
            case '+':
            case '-':
                return 1;
            case '.':
                return 3;
            case 'e':
                return 4;
            default:
                if (c >= '0' && c <= '9') {
                    return 2;
                }
        }
        return -1;
    }

    public boolean isNumber(String s) {
        int state = 0, finals = 0b101101000;
        int[][] transfer = new int[][]{{0, 1, 6, 2, -1},
                {-1, -1, 6, 2, -1},
                {-1, -1, 3, -1, -1},
                {8, -1, 3, -1, 4},
                {-1, 7, 5, -1, -1},
                {8, -1, 5, -1, -1},
                {8, -1, 6, 3, 4},
                {-1, -1, 5, -1, -1},
                {8, -1, -1, -1, -1}};
        char[] cs = s.toCharArray();
        for (char c : cs) {
            int id = make(c);
            if (id < 0) {
                return false;
            }
            state = transfer[state][id];
            if (state < 0) {
                return false;
            }
        }
        return (finals & (1 << state)) > 0;
    }
}