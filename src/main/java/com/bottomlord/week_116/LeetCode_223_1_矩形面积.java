package com.bottomlord.week_116;

/**
 * @author chen yue
 * @date 2021-09-30 18:20:24
 */
public class LeetCode_223_1_矩形面积 {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int x1 = Math.max(ax1, bx1),
            x2 = Math.min(ax2, bx2),
            y1 = Math.max(ay1, by1),
            y2 = Math.min(ay2, by2);

        boolean isCross = isCross(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
        return area(ax1, ay1, ax2, ay2) + area(bx1, by1, bx2, by2) - (isCross ? area(x1, y1, x2, y2) : 0);
    }

    private boolean isCross(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        return (ax1 >= bx1 && ax1 <= bx2 || ax2 >= bx1 && ax2 <= bx2 || bx1 >= ax1 && bx1 <= ax2 || bx2 >= ax1 && bx2 <= ax2) &&
                (ay1 >= by1 && ay1 <= by2 || ay2 >= by1 && ay2 <= by2 || by1 >= ay1 && by1 <= ay2 || by2 >= ay1 && by2 <= ay2);
    }

    private int area(int x1, int y1, int x2, int y2) {
        return Math.abs((x1 - x2) * (y1 - y2));
    }
}
