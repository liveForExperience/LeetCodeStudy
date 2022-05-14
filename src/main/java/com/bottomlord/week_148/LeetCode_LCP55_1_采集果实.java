package com.bottomlord.week_148;

/**
 * @author chen yue
 * @date 2022-05-14 10:15:56
 */
public class LeetCode_LCP55_1_采集果实 {
    public int getMinimumTime(int[] time, int[][] fruits, int limit) {
        int ans = 0;
        for (int[] fruit : fruits) {
            int index = fruit[0], sum = fruit[1];
            ans += time[index] * (sum % limit == 0 ? sum / limit : sum / limit + 1);
        }
        return ans;
    }
}
