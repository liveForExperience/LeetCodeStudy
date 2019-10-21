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