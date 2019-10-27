package com.bottomlord.contest_20191027;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contest_3_串联字符串的最大长度 {
    public int maxLength(List<String> arr) {
        List<Set<Character>> list = new ArrayList<>(arr.size());
        for (String str : arr) {
            Set<Character> set = new HashSet<>();
            char[] cs = str.toCharArray();
            for (char c : cs) {
                set.add(c);
            }

            if (set.size() == cs.length) {
                list.add(set);
            }
        }

        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            Set<Character> set = new HashSet<>(list.get(i));
            int count = set.size();
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }

                boolean flag = true;
                for (char c : list.get(j)) {
                    if (set.contains(c)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    set.addAll(list.get(j));
                }
            }

            ans = Math.max(ans, set.size());
        }

        return ans;
    }
}
