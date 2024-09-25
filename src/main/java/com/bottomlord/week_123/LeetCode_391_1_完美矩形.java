package com.bottomlord.week_123;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-11-16 09:15:07
 */
public class LeetCode_391_1_完美矩形 {
    public boolean isRectangleCover(int[][] rectangles) {
        int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE,
            x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE,
            sum = 0;

        Set<String> memo = new HashSet<>();

        for (int[] rectangle : rectangles) {
            int cx1 = rectangle[0], cy1 = rectangle[1], cx2 = rectangle[2], cy2 = rectangle[3];

            x1 = Math.min(cx1, x1);
            y1 = Math.min(cy1, y1);
            x2 = Math.max(cx2, x2);
            y2 = Math.max(cy2, y2);

            sum += (cx2 - cx1) * (cy2 - cy1);

            String[] points = new String[]{cx1 + " " + cy1, cx1 + " " + cy2, cx2 + " " + cy1, cx2 + " " + cy2};
            for (String point : points) {
                if (memo.add(point)) {
                    memo.remove(point);
                }
            }
        }

        if (sum != (x2 - x1) * (y2 - y1)) {
            return false;
        }

        return memo.size() == 4 &&
               memo.contains(x1 + " " + y1) &&
                memo.contains(x1 + " " + y2) &&
                memo.contains(x2 + " " + y1) &&
                memo.contains(x2 + " " + y2);
    }
}
