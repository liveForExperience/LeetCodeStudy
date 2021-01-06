package com.bottomlord.week_078;

import com.bottomlord.week_053.LeetCode_117_1_填充每个节点的下一个右侧节点指针II;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/1/6 12:08
 */
public class LeetCode_1711_1_大餐计数 {
    public int countPairs(int[] deliciousness) {
        Map<Integer, Integer> map = new HashMap<>();

        int mod = 1000000007, ans = 0;
        for (int num : deliciousness) {
            int power = 1;
            for (int i = 0; i <= 21; i++) {
                if (map.containsKey(power - num)) {
                    ans += map.get(power - num);
                    ans %= mod;
                }

                power *= 2;
            }

            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return ans;
    }
}
