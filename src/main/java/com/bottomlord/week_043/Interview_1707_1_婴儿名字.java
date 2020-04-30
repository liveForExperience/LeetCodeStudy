package com.bottomlord.week_043;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/4/30 8:45
 */
public class Interview_1707_1_婴儿名字 {
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        List<String[]> list = new ArrayList<>();
        Map<String, Integer> countMap = new HashMap<>();
        Map<String, String> dsuMap = new HashMap<>();

        for (String name : names) {
            int count = Integer.parseInt(name.substring(name.indexOf('(') + 1, name.indexOf(')')));
            countMap.put(name.substring(0, name.indexOf('(')), count);
        }

        for (String synonym : synonyms) {
            String name1 = synonym.substring(synonym.indexOf('(') + 1, synonym.indexOf(','));
            String name2 = synonym.substring(synonym.indexOf(',') + 1, synonym.indexOf(')'));

            while (dsuMap.containsKey(name1)) {
                name1 = dsuMap.get(name1);
            }

            while (dsuMap.containsKey(name2)) {
                name2 = dsuMap.get(name2);
            }

            if (!Objects.equals(name1, name2)) {
                int count = countMap.getOrDefault(name1, 0) + countMap.getOrDefault(name2, 0);

                String trulyName = name1.compareTo(name2) < 0 ? name1 : name2;
                String nickName = name1.compareTo(name2) < 0 ? name2 : name1;

                dsuMap.put(nickName, trulyName);

                countMap.remove(nickName);
                countMap.put(trulyName, count);
            }
        }

        String[] ans = new String[countMap.size()];
        int index = 0;
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            ans[index++] = entry.getKey() + "(" + entry.getValue() + ")";
        }
        return ans;
    }
}
