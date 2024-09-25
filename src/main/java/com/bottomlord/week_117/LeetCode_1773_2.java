package com.bottomlord.week_117;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2021-10-06 10:09:31
 */
public class LeetCode_1773_2 {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int index;
        if (Objects.equals(ruleKey, "type")) {
            index = 0;
        } else if (Objects.equals(ruleKey, "color")) {
            index = 1;
        } else {
            index = 2;
        }

        int count = 0;
        for (List<String> item : items) {
            if (Objects.equals(ruleValue, item.get(index))) {
                count++;
            }
        }

        return count;
    }
}