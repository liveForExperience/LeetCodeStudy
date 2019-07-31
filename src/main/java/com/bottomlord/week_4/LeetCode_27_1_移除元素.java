package com.bottomlord.week_4;

/**
 * @author LiveForExperience
 * @date 2019/7/31 21:25
 */
public class LeetCode_27_1_移除元素 {
    public int removeElement(int[] nums, int val) {
        int head = 0, tail = nums.length - 1;
        while (head <= tail) {
            if (nums[head] == val && head != tail) {
                nums[head] ^= nums[tail];
                nums[tail] ^= nums[head];
                nums[head] ^= nums[tail];
                tail--;
            }

            if (nums[head] != val) {
                head++;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        LeetCode_27_1_移除元素 test = new LeetCode_27_1_移除元素();
        test.removeElement(new int[]{1}, 1);
    }
}
