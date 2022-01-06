package com.bottomlord.week_130;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-01-06 22:55:15
 */
public class LeetCode_2011_1_执行操作后的变量值 {
    public int finalValueAfterOperations(String[] operations) {
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("X++", 1);
        mapping.put("++X", 1);
        mapping.put("X--", -1);
        mapping.put("--X", -1);

        int count = 0;
        for (String operation : operations) {
            count += mapping.get(operation);
        }
        return count;
    }
}
