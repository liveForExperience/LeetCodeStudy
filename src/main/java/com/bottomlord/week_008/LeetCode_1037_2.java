package com.bottomlord.week_008;

public class LeetCode_1037_2 {
    public boolean isBoomerang(int[][] points) {
        int ax = points[0][0];
        int ay = points[0][1];
        int bx = points[1][0];
        int by = points[1][1];
        int cx = points[2][0];
        int cy = points[2][1];

        double s1 = (ax - bx) * 0.1 / (ay - by);
        double s2 = (ax - cx) * 0.1 / (ay - cy);
        double s3 = (bx - cx) * 0.1 / (by - cy);

        return s1 != s2 || s1 != s3 || s2 != s3;
    }
}