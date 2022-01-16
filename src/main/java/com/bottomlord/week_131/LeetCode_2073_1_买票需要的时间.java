package com.bottomlord.week_131;

/**
 * @author chen yue
 * @date 2022-01-16 20:50:59
 */
public class LeetCode_2073_1_买票需要的时间 {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int ans = 0, i = 0, n = tickets.length;
        while (tickets[k] > 0) {
            int num = tickets[i];
            if (num == 0) {
                continue;
            }

            tickets[i++]--;
            ans++;
            i %= n;
        }

        return ans;
    }
}
