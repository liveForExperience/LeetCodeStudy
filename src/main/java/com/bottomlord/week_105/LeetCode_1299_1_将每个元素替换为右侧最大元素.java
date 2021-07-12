package com.bottomlord.week_105;

/**
 * @author ChenYue
 * @date 2021/7/12 8:52
 */
public class LeetCode_1299_1_将每个元素替换为右侧最大元素 {
    public int[] replaceElements(int[] arr) {
        int max = Integer.MIN_VALUE, len = arr.length;
        int[] ans = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) {
                ans[i] = -1;
                max = arr[i];
                continue;
            }

            ans[i] = max;
            max = Math.max(max, arr[i]);
        }

        return ans;
    }
}
