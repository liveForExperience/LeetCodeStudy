package com.bottomlord.week_030;

/**
 * @author ThinkPad
 * @date 2020/1/29 16:05
 */
public class LeetCode_1004_1_最大连续1的个数III {
    public int longestOnes(int[] A, int K) {
        int start = 0, end = 0, count = 0, len = A.length, ans = 0;
        while (end < len) {
            count += A[end] == 0 ? 1 : 0;
            while (count > K) {
                count -= A[start] == 0 ? 1 : 0;
                start++;
            }
            ans = Math.max(ans, end - start + 1);
            end++;
        }
        return ans;
    }
}
