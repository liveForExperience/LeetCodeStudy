package com.bottomlord.week_208;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-07-06 22:50:46
 */
public class LeetCode_2178_1_拆分成最多数目的多偶数之和 {
    public List<Long> maximumEvenSplit(long finalSum) {
        if (finalSum % 2 != 0) {
            return new ArrayList<>();
        }

        long cur = 2, sum = 0;
        List<Long> ans = new ArrayList<>();
        while (sum + cur <= finalSum) {
            sum += cur;
            ans.add(cur);
            cur += 2;
        }

        if (sum == finalSum) {
            return ans;
        }

        ans.set(ans.size() - 1, finalSum - sum + ans.get(ans.size() - 1));
        return ans;
    }
}
