package com.bottomlord.week_044;

/**
 * @author ChenYue
 * @date 2020/5/7 8:46
 */
public class Interview_1710_2 {
    public int majorityElement(int[] nums) {
        int count = 0, major = -1;
        for (int num : nums) {
            if (count == 0) {
                major = num;
                count++;
            } else {
                if (major == num) {
                    count++;
                } else {
                    count--;
                }
            }
        }

        return count > 0 ? major : -1;
    }
}