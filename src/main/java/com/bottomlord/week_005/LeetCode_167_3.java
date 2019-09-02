package com.bottomlord.week_005;

public class LeetCode_167_3 {
    public int[] twoSum(int[] numbers, int target) {
        int head = 0, tail = numbers.length - 1;
        while (head < tail) {
            int sum = numbers[head] + numbers[tail];
            if (sum == target) {
                return new int[]{head + 1, tail + 1};
            }

            if (sum < target) {
                head++;
            } else {
                tail--;
            }
        }
        return new int[0];
    }
}