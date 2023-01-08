package com.bottomlord.week_182;

/**
 * @author chen yue
 * @date 2023-01-08 13:12:18
 */
public class LCP61_1_气温变化趋势 {
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int n = temperatureA.length;
        int[] changeA = new int[n - 1], changeB = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            changeA[i] = getChange(temperatureA[i], temperatureA[i + 1]);
            changeB[i] = getChange(temperatureB[i], temperatureB[i + 1]);
        }

        int ans = 0, cur = 0;
        for (int i = 0; i < n - 1; i++) {
            if (changeA[i] == changeB[i]) {
                cur++;
            } else {
                ans = Math.max(ans, cur);
                cur = 0;
            }
        }

        return ans;
    }

    private int getChange(int x, int y) {
        return Integer.compare(x - y, 0);
    }
}
