package com.bottomlord.week_035;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/3/6 13:12
 */
public class Interview_57II_2 {
    public int[][] findContinuousSequence(int target) {
        List<int[]> ansList = new ArrayList<>();
        for (int n = 2; n <= target; n++) {
            int xn = target - n * (n - 1) / 2;
            if (xn < 0) {
                break;
            }

            if (xn % n == 0) {
                int x = xn / n;
                int[] arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = x + i;
                }
                ansList.add(arr);
            }
        }

        ansList.sort(Comparator.comparingInt(x -> x[0]));
        return ansList.toArray(new int[0][0]);
    }
}