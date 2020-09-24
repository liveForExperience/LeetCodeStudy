package com.bottomlord.week_064;

/**
 * @author ChenYue
 * @date 2020/9/24 8:22
 */
public class LeetCode_277_1_搜寻名人 {
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == candidate) {
                continue;
            }

            if (knows(candidate, i)) {
                return -1;
            }

            if (!knows(i, candidate)) {
                return -1;
            }
        }

        return candidate;
    }

    private boolean knows(int a, int b) {
        return false;
    }
}
