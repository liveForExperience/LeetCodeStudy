package com.bottomlord.week_079;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/1/11 8:35
 */
public class LeetCode_1202_1_交换字符串中的元素 {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (List<Integer> pair : pairs) {
            List<Integer> list = map.getOrDefault(pair.get(0), new ArrayList<>());
            list.add(pair.get(1));
            map.put(pair.get(0), list);

            List<Integer> list2 = map.getOrDefault(pair.get(1), new ArrayList<>());
            list2.add(pair.get(0));
            map.put(pair.get(1), list2);
        }


        StringBuilder ans = new StringBuilder();
        Set<Integer> memo = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            Set<Integer> set = new HashSet<>();
            PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(s::charAt));
            dfs(i, map, set, queue);
            while (!queue.isEmpty()) {
                Integer num = queue.poll();
                if (num == null || memo.contains(num)) {
                    continue;
                }

                ans.append(s.charAt(num));
                memo.add(num);
                break;
            }
        }

        return ans.toString();
    }

    private void dfs(int index, Map<Integer, List<Integer>> map, Set<Integer> memo, PriorityQueue<Integer> queue) {
        if (memo.contains(index)) {
            return;
        }

        queue.add(index);
        memo.add(index);

        List<Integer> list = map.get(index);
        if (list == null) {
            return;
        }

        for (Integer i : list) {
            dfs(i, map, memo, queue);
        }
    }
}
