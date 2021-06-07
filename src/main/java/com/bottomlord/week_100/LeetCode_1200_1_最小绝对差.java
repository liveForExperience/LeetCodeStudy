package com.bottomlord.week_100;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/6/7 9:11
 */
public class LeetCode_1200_1_最小绝对差 {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int len = arr.length;

        Arrays.sort(arr);
        int min = arr[len - 1];
        for (int i = 1; i < len; i++) {
            min = Math.min(min, arr[i] - arr[i - 1]);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == min) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i - 1]);
                list.add(arr[i]);
                ans.add(list);
            }
        }

        return ans;
    }
}
