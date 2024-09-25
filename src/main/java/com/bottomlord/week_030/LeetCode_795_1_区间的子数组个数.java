package com.bottomlord.week_030;

/**
 * @author ThinkPad
 * @date 2020/1/28 13:37
 */
public class LeetCode_795_1_区间的子数组个数 {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        return count(A, R) - count(A, L - 1);
    }

    private int count(int[] A, int target) {
        int cur = 0, ans = 0;
        for (int num : A) {
            cur = num <= target ? cur + 1 : 0;
            ans += cur;
        }
        return ans;
    }
}
