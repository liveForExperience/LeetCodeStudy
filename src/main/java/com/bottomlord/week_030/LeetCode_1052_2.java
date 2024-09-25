package com.bottomlord.week_030;

/**
 * @author ThinkPad
 * @date 2020/1/28 18:21
 */
public class LeetCode_1052_2 {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int len = customers.length, ans = grumpy[0] == 0 ? customers[0] : 0, pre = 0, start = grumpy[0] == 1 ? customers[0] : 0;;
        for (int i = 0; i < X && i < len; i++) {
            pre += grumpy[i] == 1 ? customers[i] : 0;
        }
        int sum = pre;

        for (int i = 1; i < len; i++) {
            ans += grumpy[i] == 0 ? customers[i] : 0;
            if (i <= len - X) {
                int endIndex = i + X - 1;
                int end = grumpy[endIndex] == 1 ? customers[endIndex] : 0;
                int tmp = pre - start + end;
                sum = Math.max(sum, tmp);
                start = grumpy[i] == 1 ? customers[i] : 0;
                pre = tmp;
            }
        }

        return ans + sum;
    }
}
