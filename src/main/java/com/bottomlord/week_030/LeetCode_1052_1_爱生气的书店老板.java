package com.bottomlord.week_030;

/**
 * @author ThinkPad
 * @date 2020/1/28 14:02
 */
public class LeetCode_1052_1_爱生气的书店老板 {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int ans = 0, sum = 0;
        for (int i = 0; i < customers.length; i++) {
            ans += grumpy[i] == 0 ? customers[i] : 0;
            int tmp = 0;
            for (int time = 0; time < X && i + time < customers.length; time++) {
                tmp += grumpy[i + time] == 1 ? customers[i + time] : 0;
            }
            sum = Math.max(sum, tmp);
        }
        return ans + sum;
    }
}
