package com.bottomlord.contest_20220403;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-04-03 10:44:06
 */
public class Contest_2_1_找出输掉零场或一场比赛的玩家 {
    public List<List<Integer>> findWinners(int[][] matches) {
        Set<Integer> set = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] match : matches) {
            set.add(match[0]);
            set.add(match[1]);

            map.computeIfAbsent(match[1], x -> new ArrayList<>()).add(match[0]);
        }

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ans1 = new ArrayList<>(), ans2 = new ArrayList<>();
        for (Integer n : set) {
            if (!map.containsKey(n)) {
                ans1.add(n);
            }
        }

        Collections.sort(ans1);

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                ans2.add(entry.getKey());
            }
        }

        Collections.sort(ans2);

        ans.add(ans1);
        ans.add(ans2);

        return ans;
    }
}
