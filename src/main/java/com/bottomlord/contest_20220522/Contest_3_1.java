package com.bottomlord.contest_20220522;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chen yue
 * @date 2022-05-22 10:28:15
 */
public class Contest_3_1 {
    public int minimumLines(int[][] stockPrices) {
        int n = stockPrices.length, ans = 0;
        Arrays.sort(stockPrices, Comparator.comparingInt(x -> x[0]));
        for (int i = 0; i < n - 1;) {
            int j = i + 1;
            int[] x = stockPrices[i], y = stockPrices[j];
            BigDecimal cur = count(x, y);
            while (j < n) {
                if (count(x, stockPrices[j]).equals(cur)) {
                    j++;
                } else {
                    break;
                }
            }

            ans++;

            i = j - 1;
        }

        return ans;
    }

    private BigDecimal count(int[] x, int[] y) {
        return (new BigDecimal(y[1]).subtract(new BigDecimal(x[1])))
                .divide(new BigDecimal(y[0]).subtract(new BigDecimal(y[1])), 20, RoundingMode.HALF_UP);
    }
}
