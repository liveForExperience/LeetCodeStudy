package com.bottomlord.week_190;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-03-03 08:42:41
 */
public class LeetCode_1487_1_保证文件名唯一 {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        int n = names.length;
        String[] ans = new String[n];
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            Integer val = map.get(name);

            if (val == null) {
                map.put(name, 0);
                ans[i] = name;
            } else {
                val++;
                String newName = name + "(" + val + ")";
                while (map.containsKey(newName)) {
                    newName = name + "(" + (++val) + ")";
                }

                map.put(name, val);
                map.put(newName, 0);
                ans[i] = newName;
            }
        }

        return ans;
    }
}
