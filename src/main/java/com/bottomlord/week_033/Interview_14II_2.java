package com.bottomlord.week_033;

import java.math.BigInteger;

/**
 * @author ThinkPad
 * @date 2020/2/18 9:26
 */
public class Interview_14II_2 {
    public int cuttingRope(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }

        BigInteger[] dp = new BigInteger[n + 1];
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("2");
        dp[3] = new BigInteger("3");
        for (int i = 4; i <= n; i++) {
            dp[i] = new BigInteger("0");
            for (int j = 1; j <= i /2; j++) {
                dp[i] = dp[i].max(dp[j].multiply(dp[i - j]));
            }
        }

        return dp[n].mod(new BigInteger("1000000007")).intValue();
    }
}
