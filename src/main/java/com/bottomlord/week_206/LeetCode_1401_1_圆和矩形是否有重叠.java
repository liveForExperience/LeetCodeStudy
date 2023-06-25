package com.bottomlord.week_206;

/**
 * @author chen yue
 * @date 2023-06-25 16:50:54
 */
public class LeetCode_1401_1_圆和矩形是否有重叠 {
    public boolean checkOverlap(int radius, int ccx, int ccy, int x1, int y1, int x2, int y2) {
        int tx, ty;
        if (ccx < x1 && ccy > y2) {
            tx = x1;
            ty = y2;
        } else if (ccx < x1 && ccy >= y1) {
            tx = x1;
            ty = ccy;
        } else if (ccx < x1) {
            tx = x1;
            ty = y1;
        } else if (ccx <= x2 && ccy < y1) {
            tx = ccx;
            ty = y1;
        } else if (ccx > x2 && ccy < y1) {
            tx = x2;
            ty = y1;
        } else if (ccx > x2 && ccy <= y2) {
            tx = x2;
            ty = ccy;
        } else if (ccx > x2) {
            tx = x2;
            ty = y2;
        } else if (ccy > y2) {
            tx = ccx;
            ty = y2;
        } else {
            return true;
        }

        return distance(tx, ty, ccx, ccy) <= radius * radius;
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}
