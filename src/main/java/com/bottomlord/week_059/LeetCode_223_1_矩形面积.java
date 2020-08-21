package com.bottomlord.week_059;

/**
 * @author ChenYue
 * @date 2020/8/21 8:22
 */
public class LeetCode_223_1_矩形面积 {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (A > E) {
            return computeArea(E, F, G, H, A, B, C, D);
        }

        int total = Math.abs(A - C) * Math.abs(B - D) + Math.abs(E - G) * Math.abs(F - H);
        if (B >= H || D <= F || C <= E) {
            return total;
        }

        int up = Math.min(D, H), down = Math.max(B, F), right = Math.min(C, G);
        return total - Math.abs(up - down) * Math.abs(E - right);
    }
}
