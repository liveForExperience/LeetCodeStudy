package com.bottomlord.week_086;

/**
 * @author ChenYue
 * @date 2021/3/5 8:38
 */
public class LeetCode_478_1_在园内随机生成点 {
    class Solution {
        private double rad, xc, yc;
        public Solution(double radius, double x_center, double y_center) {
            this.rad = radius;
            this.xc = x_center;
            this.yc = y_center;
        }

        public double[] randPoint() {
            double x0 = xc - rad, y0 = yc - rad;

            while (true) {
                double xr = x0 + Math.random() * 2 * rad, yr = y0 + Math.random() * 2 * rad;
                if (Math.sqrt(Math.pow(xr - xc, 2) + Math.pow(yr - yc, 2)) <= rad) {
                    return new double[]{xr, yr};
                }
            }
        }
    }
}
