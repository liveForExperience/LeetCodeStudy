package com.bottomlord.week_108;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/8/4 8:18
 */
public class LeetCode_611_1_有效三角形的个数 {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }

        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int head = j, tail = n - 1;
                while (head <= tail) {
                    int mid = head + (tail - head) / 2;
                    if (nums[mid] >= nums[i] + nums[j]) {
                        tail = mid - 1;
                    } else {
                        head = mid + 1;
                    }
                }

                if (head > j) {
                    ans += head - j - 1;
                }
            }
        }
        return ans;
    }
}
