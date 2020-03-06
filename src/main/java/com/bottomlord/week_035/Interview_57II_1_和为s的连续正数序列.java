package com.bottomlord.week_035;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/3/6 8:25
 */
public class Interview_57II_1_和为s的连续正数序列 {
    public int[][] findContinuousSequence(int target) {
        List<int[]> ansList = new ArrayList<>();
        for (int i = 1; i < target; i++) {
            int sum = 0;
            boolean find = false;
            List<Integer> list = new ArrayList<>();
            for (int j = i; j < target; j++) {
                sum += j;
                if (sum > target) {
                    break;
                }
                list.add(j);
                if (sum == target) {
                    find = true;
                    break;
                }
            }

            if (find) {
                int[] arr = new int[list.size()];
                int index = 0;
                for (int num : list) {
                    arr[index++] = num;
                }
                ansList.add(arr);
            }
        }

        return ansList.toArray(new int[0][0]);
    }
}
