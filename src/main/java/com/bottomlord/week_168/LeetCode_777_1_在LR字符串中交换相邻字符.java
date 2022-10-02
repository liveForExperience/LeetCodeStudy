package com.bottomlord.week_168;

/**
 * @author chen yue
 * @date 2022-10-02 22:20:58
 */
public class LeetCode_777_1_在LR字符串中交换相邻字符 {
    public boolean canTransform(String start, String end) {
        int x = 0, y = 0, n = start.length();
        while (x < n || y < n) {
            while (x < n && start.charAt(x) == 'X') {
                x++;
            }

            while (y < n && end.charAt(y) == 'X') {
                y++;
            }

            if (x == n || y == n) {
                return x == y;
            }

            if (start.charAt(x) != end.charAt(y)) {
                return false;
            }

            if (start.charAt(x) == 'L' && x < y) {
                return false;
            }

            if (start.charAt(x) == 'R' && x > y) {
                return false;
            }

            x++;
            y++;
        }

        return true;
    }
}
