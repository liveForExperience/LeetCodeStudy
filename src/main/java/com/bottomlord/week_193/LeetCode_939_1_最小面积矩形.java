package com.bottomlord.week_193;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-03-21 08:51:02
 */
public class LeetCode_939_1_最小面积矩形 {

    private int min = Integer.MAX_VALUE;
    private Map<Integer, List<Integer>> rmap = new HashMap<>(), cmap = new HashMap<>();
    private Set<String> memo = new HashSet<>();


    public int minAreaRect(int[][] points) {
        for (int[] point : points) {
            rmap.computeIfAbsent(point[0], x -> new ArrayList<>()).add(point[1]);
            cmap.computeIfAbsent(point[1], x -> new ArrayList<>()).add(point[0]);
        }

        for (int[] point : points) {
            LinkedList<int[]> list = new LinkedList<>();
            list.add(point);
            String key = getKey(point[0], point[1]);
            memo.add(key);
            backTrack(1, point[1], false, list);
            memo.remove(key);
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private void backTrack(int index, int target, boolean isR, LinkedList<int[]> list) {
        if (index == 4) {
            if (list.get(0)[0] == list.get(3)[0]) {
                min = Math.min(min, square(list));
            }

            return;
        }

        if (index == 3 && min <= square(list)) {
            return;
        }

        List<Integer> candidates = isR ? rmap.getOrDefault(target, new ArrayList<>()) : cmap.getOrDefault(target, new ArrayList<>());
        for (Integer candidate : candidates) {
            int nextr = isR ? target : candidate, nextc = isR ? candidate : target;
            String key = getKey(nextr, nextc);
            if (memo.contains(key)) {
                continue;
            }

            memo.add(key);
            list.addFirst(new int[]{nextr, nextc});
            backTrack(index + 1, candidate, !isR, list);
            memo.remove(key);
            list.removeFirst();
        }
    }

    private String getKey(int x, int y) {
        return x + "::" + y;
    }

    private int square(List<int[]> list) {
        int maxr, minr, maxc, minc;
        maxr = maxc = Integer.MIN_VALUE;
        minr = minc = Integer.MAX_VALUE;
        for (int[] arr : list) {
            maxr = Math.max(maxr, arr[0]);
            minr = Math.min(minr, arr[0]);
            maxc = Math.max(maxc, arr[1]);
            minc = Math.min(minc, arr[1]);
        }

        return (maxr - minr) * (maxc - minc);
    }
}
