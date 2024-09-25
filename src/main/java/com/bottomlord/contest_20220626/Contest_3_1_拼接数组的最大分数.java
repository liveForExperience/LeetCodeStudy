package com.bottomlord.contest_20220626;

/**
 * @author chen yue
 * @date 2022-06-25 23:26:46
 */
public class Contest_3_1_拼接数组的最大分数 {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] sums1 = new int[n + 1], sums2 = new int[n + 1];
        for (int i = 0; i < nums1.length; i++) {
            sums1[i + 1] = sums1[i] + nums1[i];
            sums2[i + 1] = sums2[i] + nums2[i];
        }

        int max = Math.max(sums1[n], sums2[n]);

        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int left1 = sums1[i] - sums1[0];
                int mid2 = sums2[j] - sums2[i];
                int right1 = sums1[n] - sums1[j];

                int left2 = sums2[i] - sums2[0];
                int mid1 = sums1[j] - sums1[i];
                int right2 = sums2[n] - sums2[j];

                int curMax = Math.max(left1 + mid2 + right1, left2 + mid1 + right2);
                max = Math.max(curMax, max);
            }
        }

        return max;
    }
}
