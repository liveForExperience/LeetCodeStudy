package com.bottomlord.week_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/8/5 18:56
 */
public class LeetCode_448_1_找到所有数组中消失的数字 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                int tmp = nums[i];
                nums[i] = 0;
                while (tmp > 0) {
                    int next = nums[tmp - 1];
                    nums[tmp - 1] = -1;
                    tmp = next;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                ans.add(i + 1);
            }
        }

        return ans;
    }
}
