package com.bottomlord.week_8;

public class LeetCode_1037_1_有效的回旋镖 {
    public boolean isBoomerang(int[][] points) {
        int ax = points[0][0];
        int ay = points[0][1];
        int bx = points[1][0];
        int by = points[1][1];
        int cx = points[2][0];
        int cy = points[2][1];

        double lenA = Math.sqrt(Math.pow(ax - bx, 2) + Math.pow(ay - by, 2));
        double lenB = Math.sqrt(Math.pow(ax - cx, 2) + Math.pow(ay - cy, 2));
        double lenC = Math.sqrt(Math.pow(bx - cx, 2) + Math.pow(by - cy, 2));

        return !(lenA + lenB == lenC || lenA + lenC == lenB || lenB + lenC == lenA);
    }
}
