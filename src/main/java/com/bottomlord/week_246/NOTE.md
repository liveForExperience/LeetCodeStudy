# [LeetCode_518_零钱兑换II](https://leetcode.cn/problems/coin-change-ii)
## 解法
### 思路
动态规划：
- 涂格子
- `dp[i]`: 总金额为i的情况下的所有可能数
- 状态转义方程：`dp[i] += dp[i - coin]`，当前的金额是以某种硬币金额的选择累加出来的
- base case：`dp[0] = 1`，金额为0的可能数是1，也即什么硬币都不选
- 算法过程：
  - 创建数组`dp[amount + 1]`
  - 初始化数组：`dp[0] = 1`
  - 2层循环
    - 第一层遍历所有硬币
    - 第二层通过硬币的金额一层层的涂格子，第二层的遍历区间是`[coin, amount]`
  - 遍历结束后，返回`dp[amount]`即可
### 代码
```java
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
```