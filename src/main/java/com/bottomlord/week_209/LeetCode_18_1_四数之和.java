package com.bottomlord.week_209;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-07-15 14:44:34
 */
public class LeetCode_18_1_四数之和 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        sort(nums, 0, n - 1);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int a = j + 1, b = n - 1;
                while (a < b) {
                    long sum = (long) nums[i] + nums[j] + nums[a] + nums[b];
                    if (sum < target) {
                        a++;
                    } else if (sum > target) {
                        b--;
                    } else {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[a], nums[b]));
                        while (a < b && nums[a] == nums[a + 1]) {
                            a++;
                        }
                        a++;

                        while (a < b && nums[b] == nums[b - 1]) {
                            b--;
                        }
                        b--;
                    }
                }
            }
        }

        return ans;
    }

    private void sort(int[] nums, int head, int tail) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(nums, head, tail);
        sort(nums, head, pivot - 1);
        sort(nums, pivot + 1, tail);
    }

    private int partition(int[] nums, int head, int tail) {
        int num = nums[head];
        while (head < tail) {
            while (head < tail && num <= nums[tail]) {
                tail--;
            }
            nums[head] = nums[tail];

            while (head < tail && num >= nums[head]) {
                head++;
            }
            nums[tail] = nums[head];
        }

        nums[head] = num;
        return head;
    }
}
