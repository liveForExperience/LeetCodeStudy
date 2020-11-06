package com.bottomlord.week_069;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/11/5 8:26
 */
public class LeetCode_324_1_摆动排序II {
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        int[] copy = Arrays.copyOf(nums,len);
        Arrays.sort(copy);
        int mid = len % 2 == 1 ? len / 2 : len / 2 - 1, l = mid, r = nums.length - 1, index = 0;
        while (l >= 0 || r > nums.length / 2) {
            if (l >= 0) {
                nums[index++] = copy[l--];
            }

            if (r > mid) {
                nums[index++] = copy[r--];
            }
        }
    }
}
