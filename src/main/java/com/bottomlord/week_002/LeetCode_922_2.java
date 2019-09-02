package com.bottomlord.week_002;

/**
 * @author LiveForExperience
 * @date 2019/7/19 22:18
 */
public class LeetCode_922_2 {
    public int[] sortArrayByParityII(int[] A) {
        int[] arr = new int[A.length];
        int even = 0, odd = 1;
        for (int value : A) {
            if (value % 2 == 0) {
                arr[even] = value;
                even += 2;
            } else {
                arr[odd] = value;
                odd += 2;
            }
        }

        return arr;
    }
}