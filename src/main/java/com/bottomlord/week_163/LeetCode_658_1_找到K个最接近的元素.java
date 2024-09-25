package com.bottomlord.week_163;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-08-25 22:00:30
 */
public class LeetCode_658_1_找到K个最接近的元素 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            list.add(num);
        }

        list.sort((a, b) -> {
            int disA = Math.abs(a - x), disB = Math.abs(b - x);
            if (disA != disB) {
                return disA - disB;
            }

            return a - b;
        });

        List<Integer> ans = list.subList(0, k);
        ans.sort(Comparator.naturalOrder());
        return ans;
    }
}
