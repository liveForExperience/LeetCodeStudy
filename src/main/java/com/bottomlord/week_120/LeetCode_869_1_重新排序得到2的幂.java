package com.bottomlord.week_120;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-10-28 08:35:55
 */
public class LeetCode_869_1_重新排序得到2的幂 {
    public boolean reorderedPowerOf2(int n) {
        List<Integer> nums = new ArrayList<>();
        while (n != 0) {
            nums.add(n % 10);
            n /= 10;
        }
        return dfs(nums, 0, 0, new boolean[nums.size()]);
    }

    private boolean dfs(List<Integer> nums, int count, int num, boolean[] memo) {
        if (count == nums.size()) {
            return isValid(num);
        }

        for (int i = 0; i < nums.size(); i++) {
            if (memo[i]) {
                continue;
            }

            if (num == 0 && nums.get(i) == 0) {
                continue;
            }

            num = num * 10 + nums.get(i);
            memo[i] = true;
            boolean result = dfs(nums, count + 1, num, memo);
            if (result) {
                return true;
            }
            num /= 10;
            memo[i] = false;
        }

        return false;
    }

    private boolean isValid(int num) {
        if (num == 0) {
            return false;
        }

        while (num != 1) {
            if (num % 2 != 0) {
                return false;
            }

            num /= 2;
        }

        return true;
    }
}
