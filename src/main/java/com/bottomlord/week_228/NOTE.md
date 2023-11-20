# [LeetCode_53_最大子数组和](https://leetcode.cn/problems/maximum-subarray)
## 解法
### 思路
动态规划
### 代码
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length, ans = Integer.MIN_VALUE;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = nums[i - 1] + Math.max(0, dp[i - 1]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```