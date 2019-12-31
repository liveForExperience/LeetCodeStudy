package com.bottomlord.week_026;

import java.util.LinkedList;

/**
 * @author ThinkPad
 * @date 2019/12/30 20:51
 */
public class LeetCode_207_1_课程表 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            in[prerequisite[0]]++;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                queue.offerFirst(i);
            }
        }

        while (!queue.isEmpty()) {
            int num = queue.pollFirst();
            numCourses--;

            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] != num) {
                    continue;
                }
                if (--in[prerequisite[0]] == 0) {
                    queue.offerFirst(prerequisite[0]);
                }
            }
        }

        return numCourses == 0;
    }
}
