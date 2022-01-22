package com.bottomlord.week_132;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-01-21 08:56:44
 */
public class LeetCode_1345_1_跳跃游戏IV {
    private int min;

    public int minJumps(int[] arr) {
        min = arr.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }

        dfs(0, 0, arr, map, new HashSet<>());

        return min;
    }

    private void dfs(int index, int count, int[] arr, Map<Integer, List<Integer>> map, Set<Integer> memo) {
        if (count > min) {
            return;
        }

        if (index == arr.length - 1) {
            min = count;
            return;
        }

        List<Integer> list = new ArrayList<>(map.getOrDefault(arr[index], new ArrayList<>()));
        map.remove(arr[index]);

        if (index != 0) {
            list.add(index - 1);
        }

        if (index != arr.length - 1) {
            list.add(index + 1);
        }

        for (int i : list) {
            if (i == index) {
                continue;
            }

            if (memo.contains(i)) {
                continue;
            }

            memo.add(i);
            dfs(i, count + 1, arr, map, memo);
            memo.remove(i);
        }
    }
}
