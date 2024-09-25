package com.bottomlord.week_047;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/5/28 8:12
 */
public class LeetCode_974_3 {
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for (int num : A) {
            sum += num;
            int modulus = (sum % K + K) % K;
            map.put(modulus, map.getOrDefault(modulus, 0) + 1);
        }

        int ans = 0;
        for (int num : map.values()) {
            ans += num * (num - 1) / 2;
        }
        return ans;
    }
}
