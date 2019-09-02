package com.bottomlord.week_002;

/**
 * @author LiveForExperience
 * @date 2019/7/20 11:16
 */
public class LeetCode_821_字符的最短距离 {
    public int[] shortestToChar(String S, char C) {
        char[] cs = S.toCharArray();
        int [] distance = new int[cs.length];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == C) {
                rescurse(distance, i, i, 1);
                rescurse(distance, i, i, -1);
            }
        }

        return distance;
    }

    private void rescurse(int[] distance, int index, int cur, int path) {
        if (cur < 0 || cur  > distance.length - 1) {
            return;
        }

        distance[cur] = Math.min(distance[cur], Math.abs(index - cur));

        rescurse(distance, index, cur + path, path);
    }
}
