package com.bottomlord.week_015;

public class LeetCode_789_1_逃脱阻碍者 {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int dist = manhattan(target, new int[]{0, 0});
        for (int[] ghost : ghosts) {
            if (manhattan(ghost, target) <= dist) {
                return false;
            }
        }

        return true;
    }

    private int manhattan(int[] d1, int[] d2) {
        return Math.abs(d1[0] - d2[0]) + Math.abs(d1[1] - d2[1]);
    }
}
