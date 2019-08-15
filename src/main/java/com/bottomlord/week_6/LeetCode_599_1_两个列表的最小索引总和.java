package com.bottomlord.week_6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_599_1_两个列表的最小索引总和 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>(list1.length);
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        Integer min = null;
        List<String> ansList = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            String name = list2[i];
             Integer index = map.get(name);
             if (index != null) {
                 int diff = index + i;

                 if (min == null) {
                     min = diff;
                     ansList.add(name);
                     continue;
                 }

                 if (min == diff) {
                     ansList.add(name);
                     continue;
                 }

                 if (min > diff) {
                     min = diff;
                     ansList.clear();
                     ansList.add(name);
                 }
             }
        }

        return ansList.toArray(new String[0]);
    }
}
