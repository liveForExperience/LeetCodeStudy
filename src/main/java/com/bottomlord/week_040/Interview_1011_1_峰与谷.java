package com.bottomlord.week_040;

/**
 * @author ChenYue
 * @date 2020/4/7 8:51
 */
public class Interview_1011_1_峰与谷 {
    public void wiggleSort(int[] nums) {
        int head = 0, tail = nums.length - 1, index = 0;
        int[] tmp = new int[nums.length];
        while (head <= tail) {
            if (head == tail) {
                tmp[index] = nums[head];
                break;
            }

            tmp[index++] = nums[head++];
            tmp[index++] = nums[tail--];
        }

        index = 0;
        for (int num : tmp) {
            nums[index++] = num;
        }
    }
}
