package com.bottomlord.week_019;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_756_1_金字塔转换矩阵 {
    private String str = "ABCDEFG";
    private int[] nums = {1,2,4,8,16,32,64};
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : allowed) {
            int num = nums[str.charAt(2) - 'A'];
            String key = str.substring(0, 2);
            map.put(key, map.getOrDefault(key, 0) + num);
        }

        return backTrace(map, "", bottom, 0);
    }

    private boolean backTrace(Map<String, Integer> map, String next, String cur, int index) {
        if (index == cur.length() - 1) {
            return cur.length() == 1 || backTrace(map, "", next, 0);
        }

        String key = cur.substring(index, index + 2);
        if (!map.containsKey(key)) {
            return false;
        }

        int value = map.get(key);
        for (int i = 0; i < 7 ; i++) {
            if ((value >> i & 1) == 1) {
                next += str.charAt(i);
                boolean flag = backTrace(map, next, cur, index + 1);
                if (flag) {
                    return true;
                }
                next = next.substring(0, next.length() - 1);
            }
        }
        return false;
    }
}
