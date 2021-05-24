package com.bottomlord.week_098;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/5/24 20:49
 */
public class LeetCode_1065_字符串的索引对 {
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> list = new ArrayList<>();
        for (String word : words) {
            int index = -1;
            do {
                index = text.indexOf(word, index);
                if (index != -1) {
                    list.add(new int[]{index, index + word.length()});
                    index += word.length();
                }
            } while (index != -1);
        }

        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i][0] = list.get(i)[0];
            ans[i][1] = list.get(i)[1];
        }

        Arrays.sort(ans, (x, y) -> {
            if (x[0] == y[0]) {
                return x[1] - y[1];
            }
            return x[0] - x[1];
        });
        return ans;
    }
}
