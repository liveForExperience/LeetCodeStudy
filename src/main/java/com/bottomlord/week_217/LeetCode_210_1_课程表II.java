package com.bottomlord.week_217;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2023-09-10 11:07:01
 */
public class LeetCode_210_1_课程表II {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses], inDegrees = new int[numCourses];
        List<Integer>[] edges = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }

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

        int index = 0;
        while (!queue.isEmpty()) {
            int out = queue.poll();
            ans[index++] = out;
            numCourses--;

            for (Integer in : edges[out]) {
                inDegrees[in]--;

                if (inDegrees[in] == 0) {
                    queue.offer(in);
                }
            }
        }

        return numCourses == 0 ? ans : new int[0];
    }
}
