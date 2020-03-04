package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/3 9:09
 */
public class Interview_51_2 {
    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }

        return divideAndConquer(nums, 0, len - 1, new int[nums.length]);
    }

    private int divideAndConquer(int[] nums, int left, int right, int[] tmp) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int l = divideAndConquer(nums, left, mid, tmp);
        int r = divideAndConquer(nums, mid + 1, right, tmp);

        if (nums[mid] <= nums[mid + 1]) {
            return l + r;
        }

        return l + r + mergeSortAndCount(nums, left, mid, right, tmp);
    }


    private int mergeSortAndCount(int[] nums, int left, int mid, int right, int[] tmp) {
        if (right + 1 - left >= 0) {
            System.arraycopy(nums, left, tmp, left, right + 1 - left);
        }

        int l = left, r = mid + 1, ans = 0;
        for (int i = left; i <= right; i++) {
            if (l > mid) {
                nums[i] = tmp[r++];
            } else if (r > right) {
                nums[i] = tmp[l++];
            } else if (tmp[l] <= tmp[r]) {
                nums[i] = tmp[l++];
            } else {
                nums[i] = tmp[r++];
                ans += mid - l + 1;
            }
        }

        return ans;
    }
}