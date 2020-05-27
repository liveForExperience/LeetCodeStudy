# Interview_1723_最大黑方阵
## 题目
给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。

返回一个数组 [r, c, size] ，其中 r, c 分别代表子方阵左上角的行号和列号，size 是子方阵的边长。若有多个满足条件的子方阵，返回 r 最小的，若 r 相同，返回 c 最小的子方阵。若无满足条件的子方阵，返回空数组。

示例 1:
```
输入:
[
   [1,0,1],
   [0,0,1],
   [0,0,1]
]
输出: [1,0,2]
解释: 输入中 0 代表黑色，1 代表白色，标粗的元素即为满足条件的最大子方阵
```
示例 2:
```
输入:
[
   [0,1,1],
   [1,0,1],
   [1,1,0]
]
输出: [0,0,1]
```
提示：
```
matrix.length == matrix[0].length <= 200
```
## 解法
### 思路
dfs：
- 初始化变量max为0，用来存储最大边长
- 遍历二维数组，以遍历到的元素作为左上角开始判断
    - 每次在max基础上加1，如果所有四个边都符合，那么就更新max，并再加1
    - 如果不再符合，就终止，继续二维数组的遍历
### 代码
```java
class Solution {
    public int[] findSquare(int[][] matrix) {
        int max = 0, row = matrix.length, col = matrix[0].length;
        int[] ans = new int[3];
        Arrays.fill(ans, -1);
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == 1) {
                    continue;
                }

                int len = max;
                boolean flag1 = true;
                while (r + len < row && c + len < col) {
                    boolean flag2 = true;
                    for (int i = r; i <= r + len; i++) {
                        if (matrix[i][c] != 0) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (!flag1) {
                        break;
                    }

                    for (int i = c; i <= c + len; i++) {
                        if (matrix[r][i] != 0) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (!flag1) {
                        break;
                    }

                    for (int i = c; i <= c + len; i++) {
                        if (matrix[r + len][i] != 0) {
                            len++;
                            flag2 = false;
                            break;
                        }
                    }

                    if (!flag2) {
                        continue;
                    }

                    for (int i = r; i <= r + len; i++) {
                        if (matrix[i][c + len] != 0) {
                            len++;
                            flag2 = false;
                            break;
                        }
                    }

                    if (!flag2) {
                        continue;
                    }

                    len++;
                    max = len;
                    ans[0] = r;
                    ans[1] = c;
                    ans[2] = max;
                }
            }
        }
        return ans[0] == -1 ? new int[0] : ans;
    }
}
```
# LeetCode_1139_最大的以1为边界的正方形
## 题目
给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。

示例 1：
```
输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
输出：9
```
示例 2：
```
输入：grid = [[1,1,0,0]]
输出：1
```
提示：
```
1 <= grid.length <= 100
1 <= grid[0].length <= 100
grid[i][j] 为 0 或 1
```
## 解法
### 思路
类似面试题1723解法
### 代码
```java
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int max = 0, row = grid.length, col = grid[0].length;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == 0) {
                    continue;
                }

                int len = max;
                boolean flag1 = true;
                while (r + len < row && c + len < col) {
                    boolean flag2 = true;
                    for (int i = r; i <= r + len; i++) {
                        if (grid[i][c] != 1) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (!flag1) {
                        break;
                    }

                    for (int i = c; i <= c + len; i++) {
                        if (grid[r][i] != 1) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (!flag1) {
                        break;
                    }

                    for (int i = c; i <= c + len; i++) {
                        if (grid[r + len][i] != 1) {
                            len++;
                            flag2 = false;
                            break;
                        }
                    }

                    if (!flag2) {
                        continue;
                    }

                    for (int i = r; i <= r + len; i++) {
                        if (grid[i][c + len] != 1) {
                            len++;
                            flag2 = false;
                            break;
                        }
                    }

                    if (!flag2) {
                        continue;
                    }

                    len++;
                    max = len;
                }
            }
        }
        return max * max;
    }
}
```
## 解法二
### 思路
动态规划：
- 定义：
    - `r[i][j]`：坐标[i,j]左侧连续1的个数
    - `c[i][j]`：组表[i,j]上方连续1的个数
- 状态转移方程：
    - `r[i][j] = r[i][j - 1] + 1`
    - `c[i][j] = c[i - 1][j] + 1`
- 初始化：matrix中值为1的坐标，r和c中初始化为1
- 过程：
    - 枚举二维数组matrix中所有为1的元素
    - 将r和c的当前值设置为1
    - 如果i > 0：`r[i][j] = r[i][j - 1] + 1`
    - 如果j > 0：`c[i][j] = c[i - 1][j] + 1`
    - 求min(r[i][j], c[i][j])，遍历这个长度，查看上方和左侧两条边是否也都为1，如果是，且大于暂存的max，则更新max
### 代码
```java
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int row = grid.length, col = grid[0].length, ans = 0;
        int[][] r = new int[row][col],
                c = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }

                r[i][j] = 1;
                c[i][j] = 1;

                if (i > 0) {
                    r[i][j] += r[i - 1][j];
                }

                if (j > 0) {
                    c[i][j] += c[i][j - 1];
                }

                int max = Math.min(r[i][j], c[i][j]);
                for (int k = 0; k <= max; k++) {
                    if (k > ans && c[i - k + 1][j] >= k && r[i][j - k + 1] >= k) {
                        ans = k;
                    }
                }
            }
        }
        
        return ans * ans;
    }
}
```