package com.bottomlord.week_083;

/**
 * @author ChenYue
 * @date 2021/2/8 8:28
 */
public class LeetCode_978_1_最长湍流子数组 {
    public int maxTurbulenceSize(int[] arr) {
        int left = 0, right = 0, ans = 1, n = arr.length;

        while (right < n - 1) {
            if (left == right) {
                right++;
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
            } else {
                if ((arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) ||
                    (arr[right - 1] > arr[right] && arr[right] < arr[right + 1])) {
                    right++;
                } else {
                    left = right;
                }
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
