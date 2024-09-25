package com.bottomlord.week_190;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-02-27 20:43:28
 */
public class LeetCode_826_2 {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = difficulty[i];
            arr[i][1] = profit[i];
        }

        Arrays.sort(arr, Comparator.comparingInt(x -> x[0]));
        Arrays.sort(worker);

        int i = 0, bestProfit = 0, ans = 0;
        for (int skill : worker) {
            while (i < n && skill >= arr[i][0]) {
                bestProfit = Math.max(bestProfit, arr[i][1]);
                i++;
            }

            ans += bestProfit;
        }

        return ans;
    }
}