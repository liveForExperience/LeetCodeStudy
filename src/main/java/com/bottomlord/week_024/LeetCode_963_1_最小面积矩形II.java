package com.bottomlord.week_024;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_963_1_最小面积矩形II {
    public double minAreaFreeRect(int[][] points) {
        int len = points.length;
        Point[] arr = new Point[len];
        for (int i = 0; i < len; i++) {
            arr[i] = new Point(points[i][0], points[i][1]);
        }

        Map<Integer, Map<Point, List<Point>>> seen = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                Point center = new Point(arr[i].x + arr[j].x, arr[i].y + arr[j].y);

                int distance = (arr[i].x - arr[j].x) * (arr[i].x - arr[j].x) + (arr[i].y - arr[j].y) * (arr[i].y - arr[j].y);
                if (!seen.containsKey(distance)) {
                    seen.put(distance, new HashMap<>());
                }

                if (!seen.get(distance).containsKey(center)) {
                    seen.get(distance).put(center, new ArrayList<>());
                }

                seen.get(distance).get(center).add(arr[i]);
            }
        }

        double min = Double.MAX_VALUE;
        for (Map<Point, List<Point>> map : seen.values()) {
            for (Point center : map.keySet()) {
                List<Point> candidates = map.get(center);
                int l = candidates.size();

                for (int i = 0; i < l; i++) {
                    for (int j = i + 1; j < l; j++) {
                        Point a = candidates.get(i);
                        Point b = candidates.get(j);
                        Point c = new Point(center);

                        c.translate(-b.x, -b.y);
                        double area = a.distance(b) * a.distance(c);
                        min = Math.min(min, area);
                    }
                }
            }
        }

        return min < Double.MAX_VALUE ? min : 0;
    }
}
