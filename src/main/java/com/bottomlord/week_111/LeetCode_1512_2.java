package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-22 21:37:38
 */
public class LeetCode_1512_2 {
    public int numIdenticalPairs(int[] nums) {
        int  ans = 0;
        int[] bucket = new int[101];
        for (int num : nums) {
            ans += bucket[num]++;
        }
        return ans;
    }
}