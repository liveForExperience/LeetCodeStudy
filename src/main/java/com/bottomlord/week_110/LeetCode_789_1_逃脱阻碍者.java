package com.bottomlord.week_110;

/**
 * @author chen yue
 * @date 2021-08-22 20:16:59
 */
public class LeetCode_789_1_逃脱阻碍者 {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int distance = getManhattanDistance(0, 0, target[0], target[1]);
        for (int[] ghost : ghosts) {
            if (getManhattanDistance(ghost[0], ghost[1], target[0], target[1]) - distance <= 0) {
                return false;
            }
        }

        return true;
    }

    private int getManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
