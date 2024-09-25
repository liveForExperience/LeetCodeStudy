package com.bottomlord.week_140;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2022-03-19 14:01:41
 */
public class LeetCode_624_1_数组列表中的最大距离 {
    public int maxDistance(List<List<Integer>> arrays) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        List<Integer> minIndexes = new ArrayList<>(),
                maxIndexes = new ArrayList<>();

        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> array = arrays.get(i);
            int curMin = array.get(0), curMax = array.get(array.size() - 1);

            if (curMin < min) {
                min = curMin;
                minIndexes.clear();
                minIndexes.add(i);
            } else if (curMin == min) {
                minIndexes.add(i);
            }

            if (curMax > max) {
                max = curMax;
                maxIndexes.clear();
                maxIndexes.add(i);
            } else if (curMax == max) {
                maxIndexes.add(i);
            }
        }

        if (maxIndexes.size() > 1 || minIndexes.size() > 1 || !Objects.equals(maxIndexes.get(0), minIndexes.get(0))) {
            return max - min;
        }

        int ans = 0, maxIndex = maxIndexes.get(0), minIndex = minIndexes.get(0);
        for (int i = 0; i < arrays.size(); i++) {
            if (maxIndex != i) {
                ans = Math.max(ans, max - arrays.get(i).get(0));
            }

            if (minIndex != i) {
                ans = Math.max(ans, arrays.get(i).get(arrays.get(i).size() - 1) - min);
            }
        }

        return ans;
    }
}
