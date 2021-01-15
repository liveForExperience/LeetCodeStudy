package com.bottomlord.week_079;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/1/12 8:29
 */
public class LeetCode_1203_1_项目管理 {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        List<List<Integer>> groupAds = new ArrayList<>(), itemAds = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            groupAds.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            itemAds.add(new ArrayList<>());
        }

        int[] inGroup = new int[m], inItem = new int[n];

        for (int i = 0; i < group.length; i++) {
            int curGroup = group[i];
            for (Integer beforeItem: beforeItems.get(i)) {
                int beforeGroup = group[beforeItem];
                if (curGroup != beforeGroup) {
                    groupAds.get(beforeGroup).add(curGroup);
                    inGroup[curGroup]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (Integer beforeItem : beforeItems.get(i)) {
                itemAds.get(beforeItem).add(i);
                inItem[i]++;
            }
        }

        List<Integer> orderedGroups = sort(groupAds, inGroup, m),
                      orderedItems = sort(itemAds, inItem, n);

        Map<Integer, List<Integer>> groupItemMap = new HashMap<>();
        for (Integer item : orderedItems) {
            groupItemMap.computeIfAbsent(group[item], key -> new ArrayList<>()).add(item);
        }

        List<Integer> ans = new ArrayList<>();
        for (Integer orderedGroup : orderedGroups) {
            List<Integer> items = groupItemMap.getOrDefault(orderedGroup, new ArrayList<>());
            ans.addAll(items);
        }

        return ans.stream().mapToInt(Integer :: valueOf).toArray();
    }

    private List<Integer> sort(List<List<Integer>> ads, int[] ins, int count) {
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < ins.length; i++) {
            if (ins[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer p = queue.poll();
            if (p == null) {
                continue;
            }
            ans.add(p);

            for (Integer out : ads.get(p)) {
                ins[out]--;

                if (ins[out] == 0) {
                    queue.offer(out);
                }
            }
        }

        if (ans.size() == count) {
            return ans;
        }

        return new ArrayList<>();
    }
}
