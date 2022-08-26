package com.bottomlord.week_163;

/**
 * @author chen yue
 * @date 2022-08-26 23:33:03
 */
public class LeetCode_1464_2 {
    public int maxProduct(int[] nums) {
        int one = 0, two = 0;
        for (int num : nums) {
            if (one == 0) {
                one = num;
                continue;
            }

            if (two == 0) {
                if (one > num) {
                    two = num;
                } else {
                    two = one;
                    one = num;
                }
                continue;
            }

            if (num > one) {
                two = one;
                one = num;
            } else if (num > two) {
                two = num;
            }
        }

        return (one - 1) * (two - 1);
    }
}
