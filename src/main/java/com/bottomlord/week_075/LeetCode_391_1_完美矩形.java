package com.bottomlord.week_075;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_391_1_完美矩形 {
    public boolean isRectangleCover(int[][] rectangles) {
        int x1 = Integer.MAX_VALUE, x2 = Integer.MIN_VALUE,
            y1 = Integer.MAX_VALUE, y2 = Integer.MIN_VALUE;

        Set<String> points = new HashSet<>();
        int sum = 0;

        for (int[] rectangle : rectangles) {
            int curX1 = rectangle[0], curY1 = rectangle[1], curX2 = rectangle[2], curY2 = rectangle[3];

            x1 = Math.min(x1, curX1);
            y1 = Math.min(y1, curY1);
            x2 = Math.max(x2, curX2);
            y2 = Math.max(y2, curY2);

            sum += (curX2 - curX1) * (curY2 - curY1);

            String[] curPoints = {curX1 + " " + curY1, curX1 + " " + curY2, curX2 + " " + curY1, curX2 + " " + curY2};
            for (String curPoint : curPoints) {
                if (points.contains(curPoint)) {
                    points.remove(curPoint);
                } else {
                    points.add(curPoint);
                }
            }
        }

        if (sum != (x2 - x1) * (y2 - y1)) {
            return false;
        }

        return points.size() == 4 && points.contains(x1 + " " + y1) && points.contains(x1 + " " + y2) && points.contains(x2 + " " + y1) && points.contains(x2 + " " + y2);
    }
}
