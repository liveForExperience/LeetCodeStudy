package com.bottomlord.week_093;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/4/25 10:44
 */
public class LeetCode_531_1_孤独像素I {
    public int findLonelyPixel(char[][] picture) {
        int row = picture.length, col = picture[0].length;
        int[] rc = new int[row], cc = new int[col];
        List<int[]> list = new ArrayList<>();
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (picture[r][c] == 'B') {
                    rc[r] += 1;
                    cc[c] += 1;
                    list.add(new int[]{r,c});
                }
            }
        }

        int ans = 0;
        for (int[] arr : list) {
            if (rc[arr[0]] == 1 && cc[arr[1]] == 1) {
                ans++;
            }
        }

        return ans;
    }
}
