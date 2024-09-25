package com.bottomlord.week_106;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/7/23 8:54
 */
public class LeetCode_1389_1_按既定顺序创建目标数组 {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);
        }

        int[] ans = new int[list.size()];
        int i = 0;
        for (int num : list) {
            ans[i++]= num;
        }

        return ans;
    }
}
