# [LeetCode_1155_掷骰子等于目标和的方法数](https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum)
## 解法
### 思路
动态规划：
- dp[i][j]：
  - `i`代表第`i`次掷骰子
  - `j`代表前`i`次掷骰子后的总和为`j`
  - `dp[i][j]`代表`i`次掷骰子之后得到总和为`j`时的所有组合次数
- 状态转移方程：`dp[i][j] += dp[i - 1][j - x], x ∈ [1, k]`
- base case：`dp[1][x] = 1, x ∈ [1, k]`
- 返回结果：`dp[n][target]`
- 算法过程：
  - 3层循环
    - 第1层从2开始遍历到n次，模拟掷骰子
    - 第2层遍历所有上一次计算得到的可能总和，范围在`[i - 1, target - (n - i + 1)]`
    - 第3层遍历骰子所有可能的面值
  - 循环内容套用状态转移方程进行计算
  - 遍历结束后返回`dp[n][target]`
### 代码
```java
class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 1; i <= Math.min(k, target); i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int preSum = i - 1; preSum <= target - (n - i + 1); preSum++) {
                for (int curValue = 1; curValue <= k; curValue++) {
                    int total;
                    if ((total = preSum + curValue) > target) {
                        break;
                    }

                    dp[i][total] += dp[i - 1][preSum];
                    dp[i][total] %= 1000000007;
                }
            }
        }

        return dp[n][target];
    }
}
```
# [LeetCode_2698_求一个整数的惩罚数](https://leetcode.cn/problems/find-the-punishment-number-of-an-integer)
## 解法
### 思路
- 思考过程：
  - 回溯
- 算法过程：
  - 遍历`[1,n]`范围内的所有数，对每个数做回溯，判断是否是能够达到题目要求的数字`i`，如果符合就累加平方
  - 定义回溯函数，返回值为布尔值，返回当前`i`是否能满足题目要求的状态
    - `index`入参控制回溯切分字符串的位置，然后通过计算来判断是否符合题目要求
    - `sum`入参控制每个可能搜索路径的暂定总和
    - `s`入参是当前`i`坐标平方的字符串形式
    - `target`是当前`i * i`的值
  - 回溯过程中从`index`开始，遍历所有可能坐标，切分字符串，转换成数字后累加到`sum`上
  - 通过递归，搜索到可能路径的叶子结点，判断`sum`是否与`target`（目标值，也即`i * i`）一致，如果一致就返回true，否则返回false
  - 循环过程中，如果递归返回true，则直接中断循环遍历，直接返回true
  - 如果循环结束还没有返回true，则返回false，说明当前路径的所有叶子结点都不能满足题目要求
### 代码
```java
class Solution {
  public int punishmentNumber(int n) {
    int sum = 0;
    for (int i = 1; i <= n; i++) {
      int pow = i * i;
      if (backTrack(0, Integer.toString(pow), 0, i)) {
        sum += pow;
      }
    }

    return sum;
  }

  private boolean backTrack(int index, String s, int sum, int target) {
    if (index == s.length()) {
      return sum == target;
    }

    for (int i = index; i < s.length(); i++) {
      int cur = Integer.parseInt(s.substring(index, i + 1));

      if (backTrack(i + 1, s, sum + cur, target)) {
        return true;
      }
    }

    return false;
  }
}
```