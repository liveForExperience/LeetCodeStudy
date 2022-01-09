package com.bottomlord.week_130;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-01-07 22:56:19
 */
public class LeetCode_2032_1_至少在两个数组上出现的值 {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        int[] status = new int[101];
        List<Integer> ans = new ArrayList<>();
        for (int num : nums1) {
            status[num] = 1;
        }

        for (int num : nums2) {
            if (status[num] > 1) {
                continue;
            }

            status[num]++;
            status[num] <<= 1;
            if (status[num] == 4) {
                ans.add(num);
            }
        }

        for (int num : nums3) {
            if (status[num] == 1 || status[num] == 2) {
                status[num] += 4;
                ans.add(num);
            }
        }

        return ans;
    }
}
