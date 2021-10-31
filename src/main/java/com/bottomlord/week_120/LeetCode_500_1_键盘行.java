package com.bottomlord.week_120;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-10-31 10:51:19
 */
public class LeetCode_500_1_键盘行 {
    public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>();
        String idxs = "12210111011122000010020202";
        for (String word : words) {
            char idx = idxs.charAt(word.toLowerCase().charAt(0) - 'a');
            boolean flag = true;
            for (int i = 1; i < word.length(); i++) {
                if (idxs.charAt(word.toLowerCase().charAt(i) - 'a') != idx) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                list.add(word);
            }
        }

        String[] arr = new String[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
