package com.bottomlord.week_025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2019/12/28 21:12
 */
public class LeetCode_1268_2 {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);

        int index = 0, len = products.length;
        char[] cs = searchWord.toCharArray();
        StringBuilder builder = new StringBuilder();
        String search;
        List<List<String>> ans = new ArrayList<>();

        for (char c : cs) {
            builder.append(c);
            search = builder.toString();
            int count = 0;
            List<String> result = new ArrayList<>();
            for (int i = index; i < len; i++) {
                if (products[i].startsWith(search)) {
                    result.add(products[i]);
                    count++;
                    if (count >= 3) {
                        break;
                    }
                } else {
                    if (count > 0) {
                        break;
                    }
                    index++;
                }
            }
            ans.add(result);
        }

        return ans;
    }
}