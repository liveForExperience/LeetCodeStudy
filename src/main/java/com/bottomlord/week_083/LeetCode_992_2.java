package com.bottomlord.week_083;

/**
 * @author ChenYue
 * @date 2021/2/9 12:20
 */
public class LeetCode_992_2 {
    public int subarraysWithKDistinct(int[] A, int K) {
        return helper(A, K) - helper(A, K - 1);
    }

    private int helper(int[] a, int k) {
        int len = a.length, ans = 0, left = 0, right = 0, windowCount = 0;
        int[] count = new int[len];

        while (right < len) {
            if (count[a[right]] == 0) {
                windowCount++;
            }
            count[a[right]]++;

            while (left <= right && windowCount > k) {
                count[a[left]]--;
                if (count[a[left]] == 0) {
                    windowCount--;
                }
                left++;
            }

            ans += right - left + 1;
            right++;
        }

        return ans;
    }
}