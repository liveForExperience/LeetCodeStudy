package com.bottomlord.week_048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/6/4 8:20
 */
public class LeetCode_18_1_四数之和 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        quickSort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int a = 0; a < len; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            for (int b = a + 1; b < len; b++) {
                if (b > a + 1 && nums[b - 1] == nums[b]) {
                    continue;
                }

                int c = b + 1, d = len - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum < target) {
                        c++;
                    } else if (sum > target) {
                        d--;
                    } else {
                        ans.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c] == nums[c + 1]) {
                            c++;
                        }

                        while (c < d && nums[d] == nums[d - 1]) {
                            d--;
                        }

                        c++;
                        d--;
                    }
                }
            }
        }

        return ans;
    }

    private void quickSort(int[] arr) {
        sort(0, arr.length - 1, arr);
    }

    private void sort(int head, int tail, int[] arr) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(head, tail, arr);

        sort(head, pivot, arr);
        sort(pivot + 1, tail, arr);
    }

    private int partition(int head, int tail, int[] arr) {
        int num = arr[head];

        while (head < tail) {
            while (head < tail && num <= arr[tail]) {
                tail--;
            }
            arr[head] = arr[tail];

            while (head < tail && num >= arr[head]) {
                head++;
            }

            arr[tail] = arr[head];
        }

        arr[head] = num;
        return head;
    }
}
