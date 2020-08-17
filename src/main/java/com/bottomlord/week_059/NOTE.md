# LeetCode_213_打家劫舍II
## 题目
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例1:
```
输入: [2,3,2]
输出: 3
解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
```
示例 2:
```
输入: [1,2,3,1]
输出: 4
解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。
```
## 解法
### 思路
动态规划：
- 因为首尾相连，所以可以将这个环形数组拆分成两部分：
    - 一个不包含头，只包含尾
    - 一个只包含头，不包含尾
- `dp[i]`：从0到i范围内，可以偷到的最大金额
- 状态转移方程：`dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])`：
    - 偷当前的，那么最大值就是`dp[i - 2] + nums[i]`
    - 不偷当前的，那么最大值就是`dp[i - 1]`
- base case：
    - `dp[0] = nums[0]`
    - `i > 0 && i <= 3`：`dp[i] = max(nums)`
- 返回结果：`dp[len - 1]`
### 代码
```java
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        if (len == 1) {
            return nums[0];
        }

        if (len <= 3) {
            Arrays.sort(nums);
            return nums[len - 1];
        }
        
        return Math.max(doRob(Arrays.copyOfRange(nums, 0, len - 1)), doRob(Arrays.copyOfRange(nums, 1, len)));
    }

    private int doRob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[len - 1];
    }
}
```