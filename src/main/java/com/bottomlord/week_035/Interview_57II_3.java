package com.bottomlord.week_035;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/3/7 10:11
 */
public class Interview_57II_3 {
    public int[][] findContinuousSequence(int target) {
        int head = 1, tail = 2, sum = 3;
        List<int[]> ansList = new ArrayList<>();

        while (head < target - 1 && tail < target) {
            if (sum == target) {
                int[] arr = new int[tail - head + 1];
                for (int i = 0; i < tail - head + 1; i++) {
                    arr[i] = head + i;
                }
                ansList.add(arr);
                sum -= head++;
            } else if (sum < target) {
                sum += ++tail;
            } else {
                sum -= head++;
            }
        }

        return ansList.toArray(new int[0][0]);
    }
}