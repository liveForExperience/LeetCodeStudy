package com.bottomlord.week_048;

/**
 * @author ChenYue
 * @date 2020/6/2 8:51
 */
public class LeetCode_16_1_最接近的三数之和 {
    public int threeSumClosest(int[] nums, int target) {
        quickSort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int head = i + 1, tail = nums.length - 1;
            while (head < tail) {
                int sum = nums[i] + nums[head] + nums[tail];
                if (Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }

                if (sum < target) {
                    head++;
                } else if (sum > target) {
                    tail--;
                } else {
                    return sum;
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
        sort(head, pivot - 1, arr);
        sort(pivot + 1, tail, arr);
    }

    private int partition(int head, int tail, int[] arr) {
        int num = arr[head];
        while (head < tail) {
            while (head < tail && arr[tail] >= num) {
                tail--;
            }

            arr[head] = arr[tail];

            while (head < tail && arr[head] <= num) {
                head++;
            }

            arr[tail] = arr[head];
        }

        arr[head] = num;
        return head;
    }
}
