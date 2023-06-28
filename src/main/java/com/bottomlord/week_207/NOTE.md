# [LeetCode_1186_删除一次得到子数组最大和](https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/)
## 解法
### 思路
动态规划：
- dp[i][j]：以i坐标元素结尾的已经删除j次的子数组最大和
  - i∈[0,n-1]
  - j∈[0,1]
- 状态转移方程：
  - dp[i][0] = max(p[i - 1][0] + nums[i], arr[i])
  - dp[i][1] = max(dp[i - 1][0], dp[i - 1][1] + nums[i])
- base case：
  - dp[0][0] = nums[i]
  - dp[0][1] = 0
- 求结果，暂存状态转移过程中的最大值作为结果返回即可
- 注意：
  - dp[0][1]的初始状态是0，代表以坐标0为结尾的子数组的坐标0元素被删除，总和应该是0，但是max值并不能在arr[0]和0之间比较，因为题目要求不可以为一个空数组，所以dp[0][1]这种情况实际上是非法的，其总和应该是一个非法值，所以这个时候不做比较，直接去dp[0][0]的值作为结果
### 代码
```java
class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][2];
        int max = dp[0][0] = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
            max = Math.max(Math.max(dp[i][0], dp[i][1]), max);
        }
        return max;
    }
}
```