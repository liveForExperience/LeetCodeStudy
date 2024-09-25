package com.bottomlord.week_187;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-02-07 07:50:37
 */
public class LeetCode_1604_1_警告一小时内使用相同员工卡大于等于三次的人 {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        int n = keyName.length;
        Integer[] indexes = new Integer[n];
        int[] keyTimeNum = new int[n];

        for (int i = 0; i < n; i++) {
            indexes[i] = i;
            keyTimeNum[i] = convertNum(keyTime[i]);
        }

        Arrays.sort(indexes, Comparator.comparingInt(x -> keyTimeNum[x]));

        Map<String, List<Integer>> map = new HashMap<>();
        for (Integer index : indexes) {
            map.computeIfAbsent(keyName[index], x -> new ArrayList<>()).add(keyTimeNum[index]);
        }

        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() < 3) {
                continue;
            }

            List<Integer> list = entry.getValue();
            for (int i = 2; i < list.size(); i++) {
                if (list.get(i) - list.get(i - 2) <= 60) {
                    ans.add(entry.getKey());
                    break;
                }
            }
        }

        Collections.sort(ans);

        return ans;
    }

    private int convertNum(String str) {
        String[] factors = str.split(":");
        return Integer.parseInt(factors[0]) * 60 + Integer.parseInt(factors[1]);
    }
}
