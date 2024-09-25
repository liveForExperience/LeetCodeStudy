package com.bottomlord.week_057;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/8/6 12:18
 */
public class LeetCode_336_1_回文对 {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }

                String str = words[i] + words[j];
                if (set.contains(str)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                    continue;
                }

                int head = 0, tail = str.length() - 1;
                boolean flag = true;
                while (head < tail) {
                    if (str.charAt(head++) != str.charAt(tail--)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                    set.add(str);
                }
            }
        }

        return ans;
    }
}
