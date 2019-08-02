package com.bottomlord.week_4;

/**
 * @author LiveForExperience
 * @date 2019/8/2 18:06
 */
public class LeetCode_447_1_回旋镖的数量 {
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = j + 1; k < points.length; k++) {
                    if (k == i) {
                        continue;
                    }
                    if (find(points[i], points[j], points[k])) {
                        count += 2;
                    }
                }
            }
        }
        return count;
    }

    private boolean find(int[] one, int[] two, int[] three) {
        return Math.pow(one[0] - two[0], 2) + Math.pow(one[1] - two[1], 2) == Math.pow(one[0] - three[0], 2) + Math.pow(one[1] - three[1], 2);
    }
}
