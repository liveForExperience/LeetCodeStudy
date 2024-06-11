# [LeetCode_3038_相同分数的最大操作数目I](https://leetcode.cn/problems/maximum-number-of-operations-with-the-same-score-i)
## 解法
### 思路
- 从坐标0开始依次两两为一组，求和，并在每组和都相等的情况下，对组数进行累加，累加动作直到和不想等或坐标越界为止
- 比较的过程是对`nums`数组的遍历，遍历结束后，将累价值作为结果返回即可
### 代码
```java
class Solution {
    public int maxOperations(int[] nums) {
        int i = 2, n = nums.length, sum = nums[0] + nums[1], cnt = 1;
        while (i + 1 < n && nums[i] + nums[i + 1] == sum) {
            cnt++;
            i += 2;
        }
        return cnt;
    }
}
```
# [LeetCode_3040_相同分数的最大操作数目II](https://leetcode.cn/problems/maximum-number-of-operations-with-the-same-score-ii/)
## 解法
### 思路
记忆本+递归搜索
- 通过题目限制的3种搜索方式遍历所有可能的操作数求解组合
- 通过一个二维记事本来对已经搜索过的组合进行剪枝
- 递归方程的参数：
  - 数组`nums`
  - 头坐标`head`
  - 尾坐标`tail`
  - 当前搜索目标和`sum`
  - 记事本`memo`
- 头尾坐标会基于不同的搜索情况变更
  - 头部2个被删去：`head = head + 2`
  - 头尾2个被删去：`head = head + 1`，`tail = tail - 1`
  - 尾部2个被删去：`tail = tail - 2`
- 递归的退出条件：`head >= tail`，代表数组长度已经不足2
- 递归过程中先判断记事本中是否有当前数组头尾坐标组合的搜索记录，如果有的话就返回
- 否则就判断三种组合方式的和是否和之前搜索路径保证的和一致，如果一致就继续搜索
- 3种情况判断完毕后，将结果记录在记事本中，并将结果返回
### 代码
```java
class Solution {
    public int maxOperations(int[] nums) {
        int n = nums.length;
        return Math.max(recurse(nums, 2, n - 1, nums[0] + nums[1], getMemo(n)),
                Math.max(recurse(nums, 1, n - 2, nums[0] + nums[n - 1], getMemo(n)),
                        recurse(nums, 0, n - 3, nums[n - 1] + nums[n - 2], getMemo(n)))) + 1;
    }

    private int recurse(int[] nums, int head, int tail, int sum, int[][] memo) {
        if (head >= tail) {
            return 0;
        }

        if (memo[head][tail] != -1) {
            return memo[head][tail];
        }

        int max = 0;
        if (nums[head] + nums[head + 1] == sum) {
            max = Math.max(max, recurse(nums, head + 2, tail, sum, memo) + 1);
        }

        if (nums[head] + nums[tail] == sum) {
            max = Math.max(max, recurse(nums, head + 1, tail - 1, sum, memo) + 1);
        }

        if (nums[tail] + nums[tail - 1] == sum) {
            max = Math.max(max, recurse(nums, head, tail - 2, sum, memo) + 1);
        }

        memo[head][tail] = max;
        return max;
    }

    private int[][] getMemo(int n) {
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        return memo;
    }
}
```