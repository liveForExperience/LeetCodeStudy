package com.bottomlord.contest_20220522;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chen yue
 * @date 2022-05-22 21:58:54
 */
public class Contest_3_2 {
    public int minimumLines(int[][] stockPrices) {
        int n = stockPrices.length, ans = 0;
        Arrays.sort(stockPrices, Comparator.comparingInt(x -> x[0]));
        if (n == 1) {
            return 0;
        }
        for (int i = 2; i < n; i++) {
            int x1 = stockPrices[i][0] - stockPrices[i - 1][0], y1 = stockPrices[i][1] - stockPrices[i - 1][1],
                x2 = stockPrices[i][0] - stockPrices[i - 2][0], y2 = stockPrices[i][1] - stockPrices[i - 2][1];

            if (x1 * y2 != x2 * y1) {
                ans++;
            }
        }

        return ans;
    }
}