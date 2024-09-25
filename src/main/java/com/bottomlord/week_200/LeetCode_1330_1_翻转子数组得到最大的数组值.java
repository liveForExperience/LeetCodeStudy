package com.bottomlord.week_200;

/**
 * @author chen yue
 * @date 2023-05-13 09:44:46
 */
public class LeetCode_1330_1_翻转子数组得到最大的数组值  {
    public int maxValueAfterReverse(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, n = nums.length,
            d = Integer.MIN_VALUE, base = 0;

        for (int i = 1; i < nums.length - 1; i++) {
            int a = nums[i], b = nums[i - 1], diff = Math.abs(a - b);
            base += diff;
            max = Math.max(max, Math.min(a, b));
            min = Math.min(min, Math.max(a, b));

            d = Math.max(d,
                    Math.max(
                            Math.abs(nums[0] - a - diff),
                            Math.abs(nums[n - 1] - b - diff)
                    )
            );
        }

        return base + Math.max(d, 2 * (max - min));
    }
}
