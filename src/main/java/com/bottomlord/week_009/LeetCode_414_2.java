package com.bottomlord.week_009;

public class LeetCode_414_2 {
    public int thirdMax(int[] nums) {
        int first =  nums[0];
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num < first && num > second) {
                third = second;
                second = num;
            } else if (num < second && num > third) {
                third = num;
            }
        }

        return third == Long.MIN_VALUE ? first : (int) third;
    }
}