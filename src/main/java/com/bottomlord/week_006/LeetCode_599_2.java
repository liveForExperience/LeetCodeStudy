package com.bottomlord.week_006;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_599_2 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        int list1Len = list1.length;
        int list2Len = list2.length;

        if (list1Len > list2Len) {
            return findRestaurant(list2, list1);
        }
        Map<String, Integer> map = new HashMap<>(list1Len);
        for (int i = 0; i < list1Len; i++) {
            map.put(list1[i], i);
        }

        Integer min = null;
        List<String> ansList = new ArrayList<>();
        for (int i = 0; i < list2Len; i++) {
            String name = list2[i];
            Integer index = map.get(name);
            if (index != null) {
                int sum = index + i;

                if (min == null) {
                    min = sum;
                    ansList.add(name);
                    continue;
                }

                if (min == sum) {
                    ansList.add(name);
                    continue;
                }

                if (min > sum) {
                    min = sum;
                    ansList.clear();
                    ansList.add(name);
                }
            }
        }

        return ansList.toArray(new String[0]);
    }
}