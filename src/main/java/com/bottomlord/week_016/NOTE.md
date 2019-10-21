# LeetCode_695_岛屿的最大面积
## 题目
给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。

找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)

示例 1:
```
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
```
示例 2:
```
[[0,0,0,0,0,0,0,0]]
对于上面这个给定的矩阵, 返回 0。
```
```
注意: 给定的矩阵grid 的长度和宽度都不超过 50。
```
## 解法
### 思路
- 和岛屿sink的思路一样，循环二维数组的所有下标，从该下标开始递归。
- 如果越界或遇到0就返回
- 将当前1设置成0，标记为已探索，同时计数
- 超四个方向继续递归
- 递归返回后将记录的值和现有最大值比较，保存最大值
- 循环结束返回
### 代码
```java
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int[] arr = new int[]{0};
                dfs(grid, i, j, arr);
                max = Math.max(max, arr[0]);
            }
        }
        return max;
    }

    private void dfs(int[][] grid, int row, int col, int[] arr) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length || grid[row][col] == 0) {
            return;
        }

        arr[0]++;
        grid[row][col] = 0;

        dfs(grid, row + 1, col, arr);
        dfs(grid, row - 1, col, arr);
        dfs(grid, row, col + 1, arr);
        dfs(grid, row, col - 1, arr);
    }
}
```
# LeetCode_62_不同路径
## 题目
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

例如，上图是一个7 x 3 的网格。有多少可能的路径？

说明：m 和 n 的值均不超过 100。

示例 1:
```
输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
```
示例 2:
```
输入: m = 7, n = 3
输出: 28
```
## 失败解法
### 思路
递归计数
### 失败原因
超时
### 代码
```java
class Solution {
    private int sum = 0;

    public int uniquePaths(int m, int n) {
        recurse(0, 0, m, n);
        return sum;
    }

    private void recurse(int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return;
        }
        
        if (r == m - 1 && c == n - 1) {
            sum++;
            return;
        }
        
        recurse(r + 1, c, m, n);
        recurse(r, c + 1, m, n);
    }
}
```
## 解法
### 思路
动态规划：
- dp[i][j]：保存到达当前下标的可能路径个数
- base case：第一行和第一列的dp值
- 状态转移方程：`dp[i][j] = dp[i - 1][j] + dp[i][j - 1]`
- 结果：dp[m - 1][n - 1]
### 代码
```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        dp[0][0] = 1;

        Arrays.fill(dp[0], 1);
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
```
## 解法二
### 思路
只需要使用一维数组，因为在计算当前下标可能路径时，每一列的元素可以共用一个下标，因为每一行的这一列计算得到的值会用在下一行的计算上。
### 代码
```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        
        return dp[n - 1];
    }
}
```