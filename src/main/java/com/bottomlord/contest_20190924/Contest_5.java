package com.bottomlord.contest_20190924;

import java.util.*;

public class Contest_5 {
    public int[] bonus(int n, int[][] leadership, int[][] operations) {
        Map<Integer, Set<Long>> map = new HashMap<>();
        Map<Integer, Long> dict = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            map.put(i, new HashSet<>());
        }

        for (int[] arr : leadership) {
            long num = arr[1];
            map.get(arr[0]).add(num);
        }

        for (Map.Entry<Integer, Set<Long>> entry : map.entrySet()) {
            Set<Long> tmp = new HashSet<>();
            for (Long key : entry.getValue()) {
                tmp.addAll(map.get(key.intValue()));
            }
            entry.getValue().addAll(tmp);
        }

        List<Long> list = new ArrayList<>();
        for (int[] operation : operations) {
            if (operation[0] == 1) {
                dict.put(operation[1], dict.getOrDefault(operation[1], 0L) + operation[2]);
            } else if (operation[0] == 2) {
                dict.put(operation[1], dict.getOrDefault(operation[1], 0L) + operation[2]);
                for (Long key : map.get(operation[1])) {
                    dict.put(key.intValue(), dict.getOrDefault(key.intValue(), 0L) + operation[2]);
                }
            } else if (operation[0] == 3) {
                long sum = 0;
                for (Long key : map.get(operation[1])) {
                    sum += dict.getOrDefault(key.intValue(), 0L);
                }
                sum += dict.getOrDefault(operation[1], 0L);
                list.add(sum % 1000000007);
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i).intValue();
        }
        return ans;
    }
}