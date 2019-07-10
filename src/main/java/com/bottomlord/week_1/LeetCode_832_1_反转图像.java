package com.bottomlord.week_1;

/**
 * @author LiveForExperience
 * @date 2019/7/9 13:10
 */
public class LeetCode_832_1_反转图像 {
    public int[][] flipAndInvertImage(int[][] A) {
        for (int[] row: A) {
            int last = row.length - 1;
            for (int i = 0; i <= last - i; i++) {
                if (i == last - i) {
                    row[i] = 1 - row[i];
                } else {
                    row[i] = (1- row[i]) ^ (1 - row[last - i]);
                    row[last - i] = row[i] ^ (1 - row[last - i]);
                    row[i] = row[i] ^ row[last - i];
                }

            }
        }

        return A;
    }
}
