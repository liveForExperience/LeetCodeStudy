package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-03-25 09:47:47
 */
public class LeetCode_1574_1_删除最短的子数组使剩余数组有序 {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length,  r = n - 1;
        while (r > 0 && arr[r] >= arr[r - 1]) {
            r--;
        }

        if (r == 0) {
            return 0;
        }

        int ans = r;
        for (int l = 0; l == 0 || arr[l - 1] <= arr[l]; l++) {
            while (r < n && arr[r] < arr[l]) {
                r++;
            }

            ans = Math.min(ans, r - l - 1);
        }

        return ans;
    }
}
