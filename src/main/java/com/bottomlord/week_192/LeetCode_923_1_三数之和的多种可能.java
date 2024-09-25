package com.bottomlord.week_192;

/**
 * @author chen yue
 * @date 2023-03-16 09:21:21
 */
public class LeetCode_923_1_三数之和的多种可能 {
    public int threeSumMulti(int[] arr, int target) {
        int mod = (int) 1e9 + 7, n = arr.length - 1;
        long ans = 0L;
        for (int i = 0; i < arr.length; i++) {
            int t = target - arr[i];
            int l = i + 1, r = n;

            while (l < r) {
                int sum = arr[l] + arr[r];
                if (sum < t) {
                    l++;
                } else if (sum > t) {
                    r--;
                } else if (arr[l] == arr[r]) {
                    ans += ((long)r - l + 1) * (r - l) / 2;
                    ans %= mod;
                    break;
                } else {
                    int left = 0, right = 0;

                    while (l + 1 < r && arr[l] == arr[l + 1]) {
                        left++;
                        l++;
                    }

                    while (r - 1 > l && arr[r] == arr[r + 1]) {
                        right++;
                        r--;
                    }

                    ans += (long) left * right;
                    ans %= mod;
                    l++;
                    r--;
                }
            }
        }

        return (int)ans;
    }
}
