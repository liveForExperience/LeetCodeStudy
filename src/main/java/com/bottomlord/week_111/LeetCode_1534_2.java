package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-25 21:54:07
 */
public class LeetCode_1534_2 {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length, ans = 0;
        int[] sum = new int[1001];
        for (int j = 0; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                if (Math.abs(arr[j] - arr[k]) > b) {
                    continue;
                }

                int lj = arr[j] - a, rj = arr[j] + a,
                    lk = arr[k] - c, rk = arr[k] + c;

                int l = Math.max(0, Math.max(lj, lk)), r = Math.min(1000, Math.min(rj, rk));

                if (l <= r) {
                    if (l == 0) {
                        ans += sum[r];
                    } else {
                        ans += sum[r] - sum[l - 1];
                    }
                }
            }

            for (int t = arr[j]; t <= 1000; t++) {
                sum[t]++;
            }
        }

        return ans;
    }
}
