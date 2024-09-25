package com.bottomlord.week_136;

/**
 * @author chen yue
 * @date 2022-02-17 09:10:08
 */
public class LeetCode_573_1_松鼠模拟 {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int disTreeAndNut = 0, disSquirrelAndNut = 0, max = Integer.MIN_VALUE, ans = 0;
        for (int[] nut : nuts) {
            int treeDis = distance(tree, nut);
            int squDis = distance(squirrel, nut);

            if (treeDis - squDis > max) {
                disTreeAndNut = treeDis;
                disSquirrelAndNut = squDis;
                max = treeDis - squDis;
            }

            ans += 2 * treeDis;
        }

        return ans - disTreeAndNut + disSquirrelAndNut;
    }

    private int distance(int[] x, int[] y) {
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }
}
