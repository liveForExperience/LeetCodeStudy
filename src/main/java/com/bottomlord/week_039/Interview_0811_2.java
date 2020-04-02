package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/2 8:41
 */
public class Interview_0811_2 {
    public int waysToChange(int n) {
        int mod = 1000000007;
        long ans = 0;
        for (int i = 0; i <= n / 25; i++) {
            long j = (n - 25 * i) / 10;
            long num = (j + 1) * (n / 5 - 5 * i - j + 1);
            ans = (ans + num) % mod;
        }
        return (int) ans;
    }
}
