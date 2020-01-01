package com.bottomlord.week_026;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/1/1 12:00
 */
public class LeetCode_207_3 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        int[] flag = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            adjacency.get(prerequisite[1]).add(prerequisite[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, flag, adjacency)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int i, int[] flag, List<List<Integer>> adjacency) {
        if (flag[i] == 1) {
            return true;
        }

        if (flag[i] == -1) {
            return false;
        }

        flag[i] = 1;
        for (int j : adjacency.get(i)) {
            if (dfs(j, flag, adjacency)) {
                return true;
            }
        }

        flag[i] = -1;
        return false;
    }
}
