package com.bottomlord.week_5;

public class LeetCode_167_1_两数之和II_输入有序数组 {
    public int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        boolean find = false;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    ans[0] = i + 1;
                    ans[1] = j + 1;
                    find = true;
                    break;
                }
            }
            if (find) {
                break;
            }
        }

        return find ? ans : new int[0];
    }
}
