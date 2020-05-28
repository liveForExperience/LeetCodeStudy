package com.bottomlord.week_047;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/5/27 8:52
 */
public class LeetCode_974_2 {
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ans = 0;

        for (int num : A) {
            sum += num;
            int modules = (sum % K + K) % K;
            int same = map.getOrDefault(modules, 0);
            ans += same;
            map.put(modules, same + 1);
        }

        return ans;
    }
}
