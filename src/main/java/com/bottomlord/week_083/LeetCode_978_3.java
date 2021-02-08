package com.bottomlord.week_083;

/**
 * @author ChenYue
 * @date 2021/2/8 14:59
 */
public class LeetCode_978_3 {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length, pre1 = 1, pre0 = 1, ans = 1;

        for (int i = 1; i < len; i++) {
            int cur0 = 1, cur1 = 1;
            if (arr[i- 1] < arr[i]) {
                cur0 = pre1 + 1;
            } else if (arr[i - 1] > arr[i]) {
                cur1 = pre0 + 1;
            }

            pre0 = cur0;
            pre1 = cur1;
            ans = Math.max(ans, cur0);
            ans = Math.max(ans, cur1);
        }

        return ans;
    }
}
