package com.bottomlord.week_040;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/4/10 20:59
 */
public class Interview_1613_1_平分正方形 {
    public double[] cutSquares(int[] square1, int[] square2) {
        if (square1[0] > square2[0]) {
            int[] tmp = square1;
            square1 = square2;
            square2 = tmp;
        }

        double r1 = square1[2] / 2.0, r2 = square2[2] / 2.0;
        double x1 = square1[0] + r1, x2 = square2[0] + r2;
        double y1 = square1[1] + r1, y2 = square2[1] + r2;

        double max = Math.max(y1 + r1, y2 + r2);
        if (Objects.equals(x1, x2)) {
            return new double[]{x1, y1 - r1, x1, max};
        }

        double k = (y2 - y1) / (x2 - x1), b = y1 - k * x1;
        if (Math.abs(k) <= 1) {
            double l = square1[0], r = Math.max(x1 + r1, x2 + r2);
            return new double[]{l, l * k + b, r, r * k + b};
        } else {
            double d = Math.min(square1[1], square2[1]);
            double dx = (d - b) / k, ux = (max - b) / k;

            if (dx < ux || (Objects.equals(dx, ux) && d < max)) {
                return new double[]{dx, d, ux, max};
            } else {
                return new double[]{ux, max, dx, d};
            }
        }
    }
}
