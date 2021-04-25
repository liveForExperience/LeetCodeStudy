package com.bottomlord.week_093;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/4/25 11:00
 */
public class LeetCode_533_1_孤独像素II {
    public int findBlackPixel(char[][] picture, int target) {
        int row = picture.length, col = picture[0].length;
        int[] rc = new int[row], cc = new int[col];
        Map<Integer, List<Integer>> colBMapping = new HashMap<>();
        List<int[]> list = new ArrayList<>();
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (picture[r][c] == 'B') {
                    rc[r] += 1;
                    cc[c] += 1;
                    list.add(new int[]{r,c});
                    List<Integer> indexes = colBMapping.getOrDefault(c, new ArrayList<>());
                    indexes.add(r);
                    colBMapping.put(c, indexes);
                }
            }
        }

        int ans = 0;
        for (int[] arr : list) {
            int r = arr[0], c = arr[1];
            if (rc[r] == target && cc[c] == target) {
                List<Integer> indexes = colBMapping.get(c);
                if (indexes != null) {
                    boolean match = true;
                    for (int i = 1; i < indexes.size(); i++) {
                        if (!Arrays.equals(picture[indexes.get(i - 1)], picture[indexes.get(i)])) {
                            match = false;
                            break;
                        }
                    }

                    if (match) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}
