package com.bottomlord.contest_20191019;

public class Contest_3_抛掷硬币 {
    public double probabilityOfHeads(double[] prob, int target) {
        double[] dp = new double[target + 1];
        dp[0] = 1;

        for (int i = 0; i < prob.length; i++) {
            double[] ndp = new double[target + 1];
            ndp[0] = dp[0] * (1 - prob[i]);

            for (int j = 1; j <= target; j++) {
                ndp[j] = dp[j] * (1 - prob[i]) + dp[j - 1] * prob[i];
            }
            dp = ndp;
        }

        return dp[target];
    }
}
