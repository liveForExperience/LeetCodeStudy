package com.bottomlord.week_145;

/**
 * @author chen yue
 * @date 2022-04-18 20:21:32
 */
public class LeetCode_LCP51_1_烹饪料理 {
    private int x = -1;
    private int limit;
    private int[][] cookbooks;
    private int[][] attribute;

    public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        this.cookbooks = cookbooks;
        this.attribute = attribute;
        this.limit = limit;
        backTrack(materials, null, 0, 0, 0);
        return this.x;
    }

    private void backTrack(int[] materials, int[] cost, int index, int x, int y) {
        if (y >= limit) {
            this.x = Math.max(this.x, x);
        }

        int n = cookbooks.length;
        for (int i = index; i < n; i++) {
            int[] cookbook = cookbooks[i];
            if (remove(materials, cookbook)) {
                backTrack(materials, cookbook, i + 1, x + attribute[i][0], y + attribute[i][1]);
            }

            add(materials, cookbook);
        }
    }

    private boolean remove(int[] materials, int[] cost) {
        int n = materials.length;
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (materials[i] < cost[i]) {
                flag = false;
            }

            materials[i] -= cost[i];
        }

        return flag;
    }

    private void add(int[] materials, int[] cost) {
        int n = materials.length;
        for (int i = 0; i < n; i++) {
            materials[i] += cost[i];
        }
    }
}
