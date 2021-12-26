package com.bottomlord.week_128;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2021-12-26 17:11:13
 */
public class LeetCode_1078_1_Bigram分词 {
    public String[] findOcurrences(String text, String first, String second) {
        String[] strs = text.split(" ");
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < strs.length - 3; i++) {
            if (Objects.equals(strs[i], first) &&
            Objects.equals(strs[i + 1], second)) {
                ans.add(strs[i + 2]);
            }
        }

        return ans.toArray(new String[0]);
    }
}
