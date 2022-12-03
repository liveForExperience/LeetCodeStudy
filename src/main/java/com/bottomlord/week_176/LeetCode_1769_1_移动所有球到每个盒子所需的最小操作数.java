package com.bottomlord.week_176;

/**
 * @author chen yue
 * @date 2022-12-03 14:26:04
 */
public class LeetCode_1769_1_移动所有球到每个盒子所需的最小操作数 {
    public int[] minOperations(String boxes) {
        char[] cs = boxes.toCharArray();
        int n = cs.length;
        int[] leftSums = new int[n], rightSums = new int[n], ans = new int[n];
        int sum = 0, lc = 0, rc = 0;
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            leftSums[i] = lc;

            if (c == '1') {
                sum += i;
                lc++;
            }
        }

        ans[0] = sum;

        for (int i = n - 1; i >= 0; i--) {
            rightSums[i] = rc;
            char c = cs[i];

            if (c == '1') {
                rc++;
            }
        }

        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] + leftSums[i] - rightSums[i] - (int) (cs[i] - '0');
        }

        return ans;
    }
}
