package com.bottomlord.week_117;

/**
 * @author chen yue
 * @date 2021-10-06 09:27:48
 */
public class LeetCode_414_2 {
    public int thirdMax(int[] nums) {
        int a = nums[0], b = Integer.MIN_VALUE, c = b;
        boolean meet = false;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num == Integer.MIN_VALUE) {
                meet = true;
            }

            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (a > num && num > b) {
                c = b;
                b = num;
            } else if (b > num && num > c) {
                c = num;
            }
        }

        if (meet) {
            if (b == c && c == Integer.MIN_VALUE) {
                return a;
            } else {
                return c;
            }
        } else {
            return c == Integer.MIN_VALUE ? a : c;
        }
    }
}
