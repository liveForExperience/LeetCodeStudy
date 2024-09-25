package com.bottomlord.week_182;

/**
 * @author chen yue
 * @date 2023-01-04 15:53:19
 */
public class LeetCode_1802_1_有界数组中指定下标处的最大值 {
    public int maxValue(int n, int index, int maxSum) {
        int ans = 0, sum = 0, l = index, r = index;

        while (sum <= maxSum) {
            sum += r - l + 1;
            ans++;

            r = r == n - 1 ? n - 1 : r + 1;
            l = l == 0 ? 0 : l - 1;

            if (l == 0 && r == n - 1 && n < maxSum - sum) {
                return ans + (maxSum - sum) / n;
            }
        }

        return ans - 1;
    }
}
