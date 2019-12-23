# LeetCode_486_预测赢家
## 题目
给定一个表示分数的非负整数数组。 玩家1从数组任意一端拿取一个分数，随后玩家2继续从剩余数组任意一端拿取分数，然后玩家1拿，……。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。

给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。

示例 1:
```
输入: [1, 5, 2]
输出: False
解释: 一开始，玩家1可以从1和2中进行选择。
如果他选择2（或者1），那么玩家2可以从1（或者2）和5中进行选择。如果玩家2选择了5，那么玩家1则只剩下1（或者2）可选。
所以，玩家1的最终分数为 1 + 2 = 3，而玩家2为 5。
因此，玩家1永远不会成为赢家，返回 False。
```
示例 2:
```
输入: [1, 5, 233, 7]
输出: True
解释: 玩家1一开始选择1。然后玩家2必须从5和7中进行选择。无论玩家2选择了哪个，玩家1都可以选择233。
最终，玩家1（234分）比玩家2（12分）获得更多的分数，所以返回 True，表示玩家1可以成为赢家。
```
注意:
```
1 <= 给定的数组长度 <= 20.
数组里所有分数都为非负数且不会大于10000000。
如果最终两个玩家的分数相等，那么玩家1仍为赢家。
```
## 解法
### 思路
递归，穷举所有的可能性：
- 整个样本空间可以组成一颗二叉树，从底部往上返回取头或取尾两种可能中的最大值
- 返回的时候还要判断是1还是2取值
    - 如果是1取值，就是正的
    - 如果是2取值，就是负的
    - 需要注意返回的值与当前层选择的值相加后，要考虑到负数的情况，因为每一次选择都对于选择人的最优解，所以负数需要先变为整数取两数的最大值，然后再变回来
- 最终返回到根节点时，看值是否大于等于0
### 代码
```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return dfs(nums, 0, nums.length - 1, 1) >= 0;
    }

    private int dfs(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return turn * nums[start];
        }

        int left = turn * nums[start] + dfs(nums, start + 1, end, -turn);
        int right = turn * nums[end] + dfs(nums, start, end - 1, -turn);

        return turn * Math.max(turn * left, turn * right);
    }
}
```
## 解法二
### 思路
使用动态规划：
- `dp[i][j]`：代表`i`和`j`范围内的数组中，当前选手能够获得的最大分数差
- 状态转移方程：`dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])` 
- base case：`dp[i][i] = nums[i]`
- 最终返回：`dp[i][j] >= 0`
### 代码
```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] >= 0;
    }
}
```
## 优化代码
### 思路
解法二中，状态转移只和i行和i-1行有关，所以只使用一维就可以保存需要的状态
> 无法完全理解
### 代码
```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];

        System.arraycopy(nums, 0, dp, 0, len);

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j -1]);
            }
        }

        return dp[len - 1] >= 0;
    }
}
```