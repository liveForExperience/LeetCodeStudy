package com.bottomlord.week_135;

/**
 * @author chen yue
 * @date 2022-02-09 21:26:04
 */
public class LeetCode_offerII6_2 {
    public int[] twoSum(int[] numbers, int target) {
        int head = 0, tail = numbers.length - 1;
        while (head < tail) {
            int sum = numbers[head] + numbers[tail];
            if (sum == target) {
                return new int[]{head, tail};
            } else if (sum > target) {
                tail--;
            } else {
                head++;
            }
        }

        return null;
    }
}
