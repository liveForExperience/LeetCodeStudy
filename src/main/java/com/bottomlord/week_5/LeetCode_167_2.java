package com.bottomlord.week_5;

public class LeetCode_167_2 {
    public int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        boolean find = false;
        for (int i = 0; i < numbers.length; i++) {
            int num = target - numbers[i], head = i + 1, tail = numbers.length - 1;
            while (head <= tail) {
                int mid = (tail - head) / 2 + head;
                int tmp = numbers[mid];
                if (tmp == num) {
                    ans[0] = i + 1;
                    ans[1] = mid + 1;
                    find = true;
                    break;
                }

                if (tmp < num) {
                    head = mid + 1;
                } else {
                    tail = mid - 1;
                }
            }

            if (find) {
                break;
            }
        }

        return find ? ans : new int[0];
    }
}