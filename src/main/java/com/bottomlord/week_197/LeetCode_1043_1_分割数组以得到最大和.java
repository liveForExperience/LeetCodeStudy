package com.bottomlord.week_197;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-04-19 22:26:47
 */
public class LeetCode_1043_1_分割数组以得到最大和 {
    private int k;
    private int[] arr;
    private Map<String, Integer> map;
    public int maxSumAfterPartitioning(int[] arr, int k) {
        this.arr = arr;
        this.k = k;
        this.map = new HashMap<>();
        return dfs(0, 0, 0);
    }

    private int dfs(int index, int len, int curMax) {
        if (index >= arr.length) {
            return len * curMax;
        }

        String key = index + ":" + len + ":" + curMax;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int ans = len * curMax + dfs(index + 1, 1, arr[index]);
        if (len + 1 <= k) {
            ans = Math.max(ans, dfs(index + 1, len + 1, Math.max(curMax, arr[index])));
        }

        map.put(key, ans);
        return ans;
    }
}
