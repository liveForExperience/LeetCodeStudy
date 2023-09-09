package com.bottomlord.week_217;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2023-09-09 22:43:45
 */
public class LeetCode_207_1_课程表 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] edges = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }

        int[] inDegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            edges[prerequisite[1]].add(prerequisite[0]);
            inDegrees[prerequisite[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int out = queue.poll();
            numCourses--;

            for (Integer in : edges[out]) {
                inDegrees[in]--;

                if (inDegrees[in] == 0) {
                    queue.offer(in);
                }
            }
        }

        return numCourses == 0;
    }
}
