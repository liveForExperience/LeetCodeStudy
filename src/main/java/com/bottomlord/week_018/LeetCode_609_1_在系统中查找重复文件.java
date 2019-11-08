package com.bottomlord.week_018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_609_1_在系统中查找重复文件 {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] factors = path.split(" ");
            String path1 = factors[0];
            for (int i = 1; i < factors.length; i++) {
                String factor = factors[i];
                String file = factor.substring(0, factor.indexOf('('));
                String content = factor.substring(factor.indexOf('('), factor.indexOf(')'));
                String wholePath = path1 + "/" + file;

                List<String> valueList = map.get(content);
                if (valueList == null) {
                    valueList = new ArrayList<>();
                    valueList.add(wholePath);
                } else {
                    valueList.add(wholePath);
                }

                map.put(content, valueList);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                ans.add(entry.getValue());
            }
        }

        return ans;
    }
}