package com.bottomlord.week_048;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/6/1 8:34
 */
public class Interview_1726_1_稀疏相似度 {
    public List<String> computeSimilarities(int[][] docs) {
        int len = docs.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[][] matrix = new int[len][len];
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < docs[i].length; j++) {
                List<Integer> list = map.get(docs[i][j]);

                if (list == null) {
                    list = new ArrayList<>();
                } else {
                    for (int index : list) {
                        matrix[i][index]++;
                    }
                }

                list.add(i);
                map.put(docs[i][j], list);
            }

            for (int k = 0; k < len; k++) {
                if (matrix[i][k] > 0) {
                    ans.add(k + "," + i + ": " + String.format("%.4f", (double) matrix[i][k] / (docs[i].length + docs[k].length - matrix[i][k])));
                }
            }
        }

        return ans;
    }
}
