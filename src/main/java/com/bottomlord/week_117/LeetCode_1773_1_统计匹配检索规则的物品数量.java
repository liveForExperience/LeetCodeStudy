package com.bottomlord.week_117;

import java.util.List;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2021-10-06 09:54:43
 */
public class LeetCode_1773_1_统计匹配检索规则的物品数量 {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0;
        for (List<String> item : items) {
            if (Objects.equals(ruleKey, "type")) {
                count += Objects.equals(ruleValue, item.get(0)) ? 1 : 0;
            } else if (Objects.equals(ruleKey, "color")) {
                count += Objects.equals(ruleValue, item.get(1)) ? 1 : 0;
            } else if (Objects.equals(ruleKey, "name")) {
                count += Objects.equals(ruleValue, item.get(2)) ? 1 : 0;
            }
        }

        return count;
    }
}
