package com.bottomlord.contest_20191013;

import java.util.TreeMap;

public class Contest_4_最大相等频率 {
    public int maxEqualFreq(int[] nums) {
        int[] count = new int[100010];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ans = 2;
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
            if (count[nums[i]] != 1) {
                map.put(count[nums[i]] - 1, map.get(count[nums[i]] - 1) - 1);
                if (map.get(count[nums[i]] - 1) == 0) {
                    map.remove(count[nums[i]] - 1);
                }
            }
            map.put(count[nums[i]], map.getOrDefault(count[nums[i]], 0) + 1);

            if (map.size() == 1) {
                if (map.firstKey() == 1) {
                    ans = i + 1;
                } else if (map.firstEntry().getValue() == 1) {
                    ans = i + 1;
                }
            } else if (map.size() == 2) {
                if (map.firstKey() == 1 && map.firstEntry().getValue() == 1) {
                    ans = i + 1;
                } else if (map.firstKey() + 1 == map.lastKey() && map.lastEntry().getValue() == 1) {
                    ans = i + 1;
                }
            }
        }

        return ans;
    }
}
