# LeetCode_1130_叶值的最小代价生成树
## 题目
给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
```
每个节点都有 0 个或是 2 个子节点。
数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。（知识回顾：如果一个节点有 0 个子节点，那么该节点为叶节点。）
每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
```
示例：
```
输入：arr = [6,2,4]
输出：32
解释：
有两种可能的树，第一种的非叶节点的总和为 36，第二种非叶节点的总和为 32。

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4
```
提示：
```
2 <= arr.length <= 40
1 <= arr[i] <= 15
答案保证是一个 32 位带符号整数，即小于 2^31。
```
## 解法
### 思路
动态规划：
- 题目中需要使用到的概念
    - `maxValue[i][j]`：数组i到j的范围内的最大值，用来作为左右子树中被用来计算生成代价的因子
    - `dp[i][j]`：数组i到j的范围内，最小生成成代价的值
- 初始化：
    - `maxValue[i][i]`：
        - 通过嵌套循环，确定`maxValue`的i和j，定义好边界
        - 计算这个范围内的所有元素中的最大值，所以还需要再进行一层循环
        - 获得最大值后，存入`maxValue[i][j]`中
    - `dp[i][j]`：
        - `dp[i][i]`：都赋值为0，因为存的是非叶子节点最大值乘积的总和，dp[i][i]代表了叶子节点，所以是0
        - `dp[i][j]`：全部赋值为int的最大值
- 状态转移方程：`dp[i][j] = dp[i][k] + dp[k + 1][j] + maxValue[i][k] * maxValue[k + 1][j]`
- 结果返回：`dp[0][len - 1]`
### 代码
```java
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int len = arr.length;
        int[][] maxValue = new int[len][len], dp = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int max = 0;
                for (int k = i; k <= j; k++) {
                    max = Math.max(max, arr[k]);
                }
                maxValue[i][j] = max;
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < len; i++) {
            dp[i][i] = 0;
        }

        for (int range = 1; range < len; range++) {
            for (int start = 0; start < len - range; start++) {
                for (int mid = start; mid < start + range; mid++) {
                    dp[start][start + range] = Math.min(dp[start][start + range], dp[start][mid] + dp[mid + 1][start + range] + maxValue[start][mid] * maxValue[mid + 1][start + range]);
                }
            }
        }

        return dp[0][len - 1];
    }
}
```