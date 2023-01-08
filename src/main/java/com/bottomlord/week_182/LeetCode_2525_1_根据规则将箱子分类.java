package com.bottomlord.week_182;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-01-08 11:16:22
 */
public class LeetCode_2525_1_根据规则将箱子分类 {
    public String categorizeBox(int length, int width, int height, int mass) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Bulky");
        map.put(1 << 1, "Heavy");
        map.put(1 | (1 << 1), "Both");
        map.put(0, "Neither");

        int x = 10000, y = 1000000000, z = 100;
        int key = ((length >= x || width >= x || height >= x || (long) length * width * height >= y) ? 1 : 0) |
                (mass >= z ? 1 << 1 : 0);
        return map.get(key);
    }
}
