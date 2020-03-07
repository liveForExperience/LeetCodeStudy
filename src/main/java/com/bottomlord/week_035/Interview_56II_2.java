package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/7 14:42
 */
public class Interview_56II_2 {
    public int singleNumber(int[] nums) {
        int ans = 0, sup = 0;
        for (int num : nums) {
            ans = (ans ^ num) & ~sup;
            sup = (sup ^ num) & ~ans;
        }
        return ans;
    }
}