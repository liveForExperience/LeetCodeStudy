package com.bottomlord.week_216;

/**
 * @author chen yue
 * @date 2023-09-02 17:02:10
 */
public class LeetCode_2240_3 {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        if (cost1 < cost2) {
            return waysToBuyPensPencils(total, cost2, cost1);
        }

        long n = total / cost1, sum = 0;
        for (long i = 0; i <= n; i++) {
            long rest = total - i * cost1;
            sum += rest / cost2 + 1;
        }
        return sum;
    }
}
