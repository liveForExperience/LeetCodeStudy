package com.bottomlord.week_012;

public class LeetCode_540_2 {
    public int singleNonDuplicate(int[] nums) {
        int head = 0, tail = nums.length - 1;
        while (head <= tail) {
            if (head == tail) {
                return nums[head];
            }

            int mid = head + (tail - head) / 2;
            if (mid == 0) {
                if (nums[mid] != nums[mid + 1]) {
                    return nums[mid];
                }
            }

            if (mid == nums.length - 1) {
                if (nums[mid] != nums[mid - 1]) {
                    return nums[mid];
                }
            }

            if (mid % 2 == 0) {
                if (nums[mid] == nums[mid - 1]) {
                    tail = mid - 1;
                    continue;
                }

                if (nums[mid] == nums[mid + 1]) {
                    head = mid + 1;
                    continue;
                }

                return nums[mid];
            } else {
                if (nums[mid] == nums[mid + 1]) {
                    tail = mid - 1;
                    continue;
                }

                if (nums[mid] == nums[mid - 1]) {
                    head = mid + 1;
                    continue;
                }

                return nums[mid];
            }
        }
        return 0;
    }
}