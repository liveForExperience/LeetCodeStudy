package com.bottomlord.week_033;

import java.math.BigInteger;

/**
 * @author ThinkPad
 * @date 2020/2/18 8:59
 */
public class Interview_14II_1_剪绳子II {
    public int cuttingRope(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        dp[1] = new BigInteger("1");
        for (int i = 2; i <= n; i++) {
            dp[i] = new BigInteger("0");
            for (int j = 0; j <= i /2; j++) {
                dp[i] = dp[i].max(dp[j].max(new BigInteger("" + j)).multiply(dp[i - j].max(new BigInteger(i - j + ""))));
            }
        }

        return dp[n].mod(new BigInteger("1000000007")).intValue();
    }
}
