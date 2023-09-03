package com.bottomlord.week_216;

/**
 * @author chen yue
 * @date 2023-09-02 14:13:40
 */
public class LeetCode_2240_1_买钢笔和铅笔的方案数 {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        int ans = 0;
        for (int c1 = 0; c1 * cost1 <= total; c1++) {
            for (int c2 = 0; c1 * cost1 + c2 * cost2 <= total; c2++) {
                ans++;
            }
        }
        return ans;
    }
}
