package com.bottomlord.week_073;

/**
 * @author ChenYue
 * @date 2020/11/30 14:33
 */
public class LeetCode_493_2 {
    private int count = 0;
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        mergeSort(nums, 0, nums.length - 1);
        return count;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        int i = left, j = mid + 1;
        while (i <= mid) {
            while (j <= right && (long)nums[i] > (long)nums[j] * 2) {
                j++;
            }

            count += j - mid - 1;
            i++;
        }

        int[] tmp = new int[right - left + 1];
        int index = 0;
        i = left;
        j = mid + 1;

        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                tmp[index++] = nums[i++];
            } else {
                tmp[index++] = nums[j++];
            }
        }

        while (i <= mid) {
            tmp[index++] = nums[i++];
        }

        while (j <= right) {
            tmp[index++] = nums[j++];
        }

        for (i = 0, j = left; j <= right; i++, j++) {
            nums[j] = tmp[i];
        }
    }
}
