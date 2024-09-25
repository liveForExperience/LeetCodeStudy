package com.bottomlord.contest_20220424;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-04-24 14:16:39
 */
public class Contest_3_2 {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        Arrays.sort(rectangles, (x, y) -> y[1] - x[1]);
        int n = points.length;
        Integer[] ids = new Integer[n];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }

        Arrays.sort(ids, (x, y) -> points[y][1] - points[x][1]);

        int[] ans = new int[n];
        List<Integer> xs = new ArrayList<>();
        int index = 0;
        for (Integer id : ids) {
            int start = index;
            while (index < rectangles.length && rectangles[index][1] >= points[id][1]) {
                xs.add(rectangles[index][0]);
                index++;
            }

            if (index > start) {
                Collections.sort(xs);
            }

            ans[id] = index - binarySearch(xs, points[id][0]);
        }

        return ans;
    }

    private int binarySearch(List<Integer> xs, int target) {
        int head = 0, tail = xs.size();
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (xs.get(mid) < target) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return head;
    }
}
