package com.bottomlord.contest_20220605;

/**
 * @author chen yue
 * @date 2022-06-05 10:28:56
 */
public class Contest_3_1_替换数组中元素 {
    public int minMaxGame(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int[] newNums = new int[n / 2];
        boolean flag = true;
        for (int i = 0; i < n; i+=2) {
            if (flag) {
                newNums[i / 2] = Math.min(nums[i], nums[i + 1]);
            } else {
                newNums[i / 2] = Math.max(nums[i], nums[i + 1]);
            }
            flag = !flag;
        }

        return minMaxGame(newNums);
    }
}
