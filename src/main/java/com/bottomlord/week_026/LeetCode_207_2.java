package com.bottomlord.week_026;

/**
 * @author ThinkPad
 * @date 2019/12/31 11:36
 */
public class LeetCode_207_2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] adjacency = new int[numCourses][numCourses];
        int[] flag = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            adjacency[prerequisite[1]][prerequisite[0]] = 1;
        }

        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, flag, adjacency)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int i, int[] flag, int[][] adjacency) {
        if (flag[i] == 1) {
            return true;
        }

        if (flag[i] == -1) {
            return false;
        }

        flag[i] = 1;
        for (int j = 0; j < adjacency.length; j++) {
            if (adjacency[i][j] == 1 && dfs(j, flag, adjacency)) {
                return true;
            }
        }

        flag[i] = -1;
        return false;
    }
}