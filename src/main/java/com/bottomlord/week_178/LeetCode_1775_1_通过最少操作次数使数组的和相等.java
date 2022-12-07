package com.bottomlord.week_178;

/**
 * @author chen yue
 * @date 2022-12-07 08:37:07
 */
public class LeetCode_1775_1_通过最少操作次数使数组的和相等 {
    public int minOperations(int[] nums1, int[] nums2) {
        int sums1 = 0, sums2 = 0;
        for (int num : nums1) {
            sums1 += num;
        }

        for (int num : nums2) {
            sums2 += num;
        }

        if (sums1 == sums2) {
            return 0;
        } else if (sums1 < sums2) {
            int[] tmpArr = nums2;
            nums2 = nums1;
            nums1 = tmpArr;

            int tmp = sums1;
            sums1 = sums2;
            sums2 = tmp;
        }

        int[] arr = new int[6];
        for (int num : nums1) {
            arr[num - 1]++;
        }

        for (int num : nums2) {
            arr[6 - num]++;
        }

        int diff = sums1 - sums2, count = 0;
        for (int i = 5; i >= 0; i--) {
            while (arr[i]-- > 0) {
                count++;
                if (diff <= i) {
                    return count;
                } else {
                    diff -= i;
                }
            }
        }

        return -1;
    }
}
