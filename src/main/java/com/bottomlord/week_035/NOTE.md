# Interview_47_礼物的最大值
## 题目
在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

示例 1:
```
输入: 
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 12
解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
```
提示：
```
0 < grid.length <= 200
0 < grid[0].length <= 200
```
## 解法
### 思路
递归+记忆化搜索
- 使用map存储已经遍历过的`x,y`位起点的路径上的最大值
- 递归退出条件是坐标越界
- 过程中就是判断向右或向下的节点，`visited`中是否已经存储，如果已经存储就直接返回，否则递归下去求得这个路径的最大值
- 最终返回当前节点值与向下或向右的最大值之和
### 代码
```java
class Solution {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        return dfs(grid, 0, 0, grid.length, grid[0].length, new HashMap<>());
    }

    private int dfs(int[][] grid, int x, int y, int row, int col, Map<Integer, Integer> visited) {
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return 0;
        }

        int right = visited.containsKey(1000 * x + y + 1) ? visited.get(1000 * x + y + 1) : dfs(grid, x, y + 1, row, col, visited);
        int down = visited.containsKey(1000 * (x + 1) + y) ? visited.get(1000 * (x + 1) + y) : dfs(grid, x + 1, y, row, col, visited);
        
        int max = grid[x][y] + Math.max(right, down);
        visited.put(1000 * x + y, max);
        return max;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[x][y]`：坐标`x,y`为起点的最大路径值
- 状态转移方程：`dp[x][y] = grid[x][y] + max(dp[x + 1][y], dp[x][y + 1])`
- 初始化：`dp[row - 1][col - 1] = grid[row - 1][col - 1]`
- 返回结果：`dp[0][0]`
- 过程：嵌套循环所有二维数组节点，从行和列的最大值开始向前遍历，遍历中执行状态转移方程
### 代码
```java
class Solution {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = grid[row - 1][col - 1];
        
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.max(i + 1 < row ? dp[i + 1][j] : 0, j + 1 < col ? dp[i][j + 1] : 0);
            }
        }
        
        return dp[0][0];
    }
}
```
## 解法三
### 思路
- 因为只能向右和向下，所以第一行和第一列的值可以处理成途径节点的累加值
- 然后通过这一行一列的初始值，开始状态转移
### 代码
```java
class Solution {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, col = grid[0].length;

        for (int i = 1; i < row; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        
        for (int i = 1; i < col; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        
        return grid[row - 1][col - 1];
    }
}
```