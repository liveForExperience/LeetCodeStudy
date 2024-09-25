package com.bottomlord.week_187;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-02-07 09:33:48
 */
public class LeetCode_1604_2 {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            map.computeIfAbsent(keyName[i], x -> new ArrayList<>()).add(convertNum(keyTime[i]));
        }

        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() < 3) {
                continue;
            }

            List<Integer> list = entry.getValue();
            Collections.sort(list);

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
        return ((str.charAt(0) - '0') * 10 + (str.charAt(1) - '0')) * 60 + (str.charAt(3) - '0') * 10 + (str.charAt(4) - '0');
    }
}
