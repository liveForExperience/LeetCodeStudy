# LeetCode_892_三维形体的表面积
## 题目
在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。

每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。

请你返回最终形体的表面积。

示例 1：
```
输入：[[2]]
输出：10
```
示例 2：
```
输入：[[1,2],[3,4]]
输出：34
```
示例 3：
```
输入：[[1,0],[0,2]]
输出：16
示例 4：
```
输入：[[1,1,1],[1,0,1],[1,1,1]]
输出：32
示例 5：

输入：[[2,2,2],[2,1,2],[2,2,2]]
输出：46

提示：
```
1 <= N <= 50
0 <= grid[i][j] <= 50
```
## 解法
### 思路
把表面积分成6部分
- 上下两面：(N * N - 为0的元素) * 2
- 4个面：
    - 第1行的v的和
    - 第N行的v的和
    - 第1列的v的和
    - 第N列的v的和
- 上面参差不齐露出的部分

难点在露出的部分，需要遍历二维数组，和相邻4个位置的v作比较，大于相邻的部分就累加表面积里
### 代码
```java
class Solution {
    public int surfaceArea(int[][] grid) {
        return topBottom(grid) + side(grid) + diff(grid);
    }

    private int diff(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    sum-=2;
                    continue;
                }
                
                if (!outOfRnage(i - 1, j, grid)) {
                    int dif = grid[i][j] - grid[i - 1][j];
                    sum += dif > 0 ? dif : 0;
                }

                if (!outOfRnage(i + 1, j, grid)) {
                    int dif = grid[i][j] - grid[i + 1][j];
                    sum += dif > 0 ? dif : 0;
                }

                if (!outOfRnage(i, j - 1, grid)) {
                    int dif = grid[i][j] - grid[i][j - 1];
                    sum += dif > 0 ? dif : 0;
                }

                if (!outOfRnage(i, j + 1, grid)) {
                    int dif = grid[i][j] - grid[i][j + 1];
                    sum += dif > 0 ? dif : 0;
                }
            }
        }

        return sum;
    }

    private int side(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid[0].length; i++) {
            sum += grid[0][i];
        }

        int lastRow = grid.length - 1;
        for (int i = 0; i < grid[lastRow].length; i++) {
            sum += grid[lastRow][i];
        }

        for (int[] row : grid) {
            sum += row[0] + row[row.length - 1];
        }
        return sum;
    }

    private int topBottom(int[][] grid) {
        return grid.length * grid.length * 2;
    }

    private boolean outOfRnage(int row, int col, int[][] grid) {
        return row < 0 || col < 0 || row >= grid.length || col >= grid[row].length;
    }
}
```
## 解法二
### 思路
遍历二维数组：
- 先把当前坐标上的立方体的表面积算出：v * 4 + 2，累加
- 和横坐标上前一个元素比较大小，谁堆叠的立方体少，谁的一面就会被遮盖，当然长的那个立方体的一面也会遮盖，把这两个面从累加值中减去
- 同理和纵坐标也做一次比较
- 遍历结束，返回累加值
### 代码
```java
class Solution {
    public int surfaceArea(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    sum += grid[i][j] * 4 + 2;
                }
                
                if (i != 0) {
                    sum -= Math.min(grid[i][j], grid[i - 1][j]) * 2;
                }
                
                if (j != 0) {
                    sum -= Math.min(grid[i][j], grid[i][j - 1]) * 2;
                }
            }
        }
        
        return sum;
    }
}
```